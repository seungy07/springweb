-- =====================================================
-- 마스크팩 제조공정 모니터링 및 품질관리 시스템
-- 데이터베이스명 : maskpack_mes
-- 총 테이블 수 : 10개
-- =====================================================
DROP DATABASE maskpack_mes;
CREATE DATABASE IF NOT EXISTS maskpack_mes;
USE maskpack_mes;


-- =====================================================
-- 1. 사용자 테이블
-- =====================================================

CREATE TABLE users (

    user_id INT AUTO_INCREMENT PRIMARY KEY,

    login_id VARCHAR(30) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    user_name VARCHAR(30) NOT NULL,

    role VARCHAR(30) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP

);


-- =====================================================
-- 2. 생산 LOT 테이블
-- =====================================================

CREATE TABLE batches (

    batch_id VARCHAR(30) PRIMARY KEY,

    product_code VARCHAR(30) NOT NULL,
    product_name VARCHAR(100),

    target_bulk_kg DECIMAL(12,4),
    actual_bulk_kg DECIMAL(12,4),

    target_units INT,
    actual_units INT,
    defect_units INT,

    start_time DATETIME(3),
    end_time DATETIME(3),

    status VARCHAR(30),

    operator_id VARCHAR(30),
    tank_id VARCHAR(30),

    record_source VARCHAR(20) NOT NULL DEFAULT 'ORIGINAL'

);


-- =====================================================
-- 3. 원료 칭량이력 테이블
-- 공정 ① 원료 칭량
-- =====================================================

CREATE TABLE material_dispensing (

    dispense_id VARCHAR(100) PRIMARY KEY,

    batch_id VARCHAR(30) NOT NULL,

    material_code VARCHAR(40),
    material_name VARCHAR(150),

    raw_material_lot VARCHAR(50),

    target_qty_kg DECIMAL(12,4),
    actual_qty_kg DECIMAL(12,4),

    weighed_by VARCHAR(30),
    dispensed_at DATETIME(3),

    status VARCHAR(50),

    CONSTRAINT fk_dispensing_batch
        FOREIGN KEY (batch_id)
        REFERENCES batches(batch_id)

);


-- =====================================================
-- 4. 공정 실행이력 테이블
-- 5개 축약 공정의 진행상태 및 실행시간
-- =====================================================

CREATE TABLE process_execution (

    execution_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    batch_id VARCHAR(30) NOT NULL,

    process_code VARCHAR(30) NOT NULL,

    start_time DATETIME(3),
    end_time DATETIME(3),

    duration_min DECIMAL(10,2),

    status VARCHAR(30),

    record_source VARCHAR(20) NOT NULL DEFAULT 'ORIGINAL',

    CONSTRAINT uq_batch_process
        UNIQUE (batch_id, process_code),

    CONSTRAINT fk_execution_batch
        FOREIGN KEY (batch_id)
        REFERENCES batches(batch_id)

);


-- =====================================================
-- 5. 공정 센서 측정이력 테이블
-- 공정 ② 가열·혼합 / ③ 냉각·마무리
-- =====================================================

CREATE TABLE sensor_telemetry (

    sensor_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    execution_id BIGINT NOT NULL,

    `timestamp` DATETIME(3) NOT NULL,

    tank_temp_c DECIMAL(8,3),

    paddle_rpm DECIMAL(10,2),

    homomixer_rpm DECIMAL(10,2),

    bulk_viscosity_cps DECIMAL(12,3),

    ph_level DECIMAL(6,3),

    motor_torque_pct DECIMAL(6,2),

    vacuum_kpa DECIMAL(8,3),

    cooling_valve_pct DECIMAL(6,2),

    record_source VARCHAR(20) NOT NULL DEFAULT 'ORIGINAL',

    CONSTRAINT fk_sensor_execution
        FOREIGN KEY (execution_id)
        REFERENCES process_execution(execution_id),

    INDEX idx_sensor_execution_time
        (execution_id, `timestamp`)

);


-- =====================================================
-- 6. 벌크 품질검사 테이블
-- 공정 ④ 벌크 품질검사
-- =====================================================

CREATE TABLE bulk_qc (

    qc_id VARCHAR(50) PRIMARY KEY,

    batch_id VARCHAR(30) NOT NULL,

    sample_time DATETIME(3),

    inspector_id VARCHAR(30),

    ph_measured DECIMAL(6,3),
    ph_criteria VARCHAR(30),

    viscosity_measured DECIMAL(12,3),
    viscosity_criteria VARCHAR(30),

    specific_gravity DECIMAL(8,4),
    sg_criteria VARCHAR(30),

    appearance_code VARCHAR(50),

    microbubble_code VARCHAR(50),

    microbial_cfu INT,

    overall_qc_result VARCHAR(40),

    qc_notes_code VARCHAR(100),

    record_source VARCHAR(20) NOT NULL DEFAULT 'ORIGINAL',

    CONSTRAINT fk_bulk_qc_batch
        FOREIGN KEY (batch_id)
        REFERENCES batches(batch_id)

);


-- =====================================================
-- 7. 충진·포장 및 최종검사 테이블
-- 공정 ⑤ 충진·포장 및 최종검사
-- =====================================================

CREATE TABLE filling_packaging (

    pouch_id VARCHAR(100) PRIMARY KEY,

    batch_id VARCHAR(30) NOT NULL,

    packaging_line VARCHAR(40),

    `timestamp` DATETIME(3),

    sheet_material_code VARCHAR(50),

    sheet_lot_no VARCHAR(50),

    sheet_dry_weight_g DECIMAL(8,3),

    fill_weight_1st_g DECIMAL(8,3),

    fill_weight_2nd_g DECIMAL(8,3),

    essence_net_weight_g DECIMAL(8,3),

    pouch_tare_weight_g DECIMAL(8,3),

    gross_total_weight_g DECIMAL(8,3),

    upper_seal_temp_c DECIMAL(8,3),

    lower_seal_temp_c DECIMAL(8,3),

    seal_pressure_bar DECIMAL(8,3),

    n2_residual_o2_pct DECIMAL(6,3),

    checkweigher_status VARCHAR(40),

    metal_detector_status VARCHAR(40),

    vision_inspection_status VARCHAR(40),

    final_disposition VARCHAR(40),

    record_source VARCHAR(20) NOT NULL DEFAULT 'ORIGINAL',

    CONSTRAINT fk_packaging_batch
        FOREIGN KEY (batch_id)
        REFERENCES batches(batch_id),

    INDEX idx_packaging_batch_time
        (batch_id, `timestamp`),

    INDEX idx_packaging_result
        (final_disposition)

);


-- =====================================================
-- 8. 이상 감지 규칙 및 대응 가이드 테이블
-- =====================================================

CREATE TABLE anomaly_rule (

    rule_id INT AUTO_INCREMENT PRIMARY KEY,

    process_code VARCHAR(30) NOT NULL,

    anomaly_type VARCHAR(50) NOT NULL,

    sensor_name VARCHAR(50),

    condition_type VARCHAR(20) NOT NULL DEFAULT 'RANGE',

    warning_min DECIMAL(12,3),
    warning_max DECIMAL(12,3),

    critical_min DECIMAL(12,3),
    critical_max DECIMAL(12,3),

    status_value VARCHAR(50),

    duration_seconds INT DEFAULT 0,

    check_items TEXT,

    response_description TEXT,

    is_active BOOLEAN NOT NULL DEFAULT TRUE

);


-- =====================================================
-- 9. 이상 발생 및 관리자 대응이력 테이블
-- =====================================================

CREATE TABLE anomaly_event (

    anomaly_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    batch_id VARCHAR(30) NOT NULL,

    pouch_id VARCHAR(100),

    rule_id INT,

    source_alarm_id VARCHAR(50) UNIQUE,

    process_code VARCHAR(30) NOT NULL,

    anomaly_type VARCHAR(50) NOT NULL,

    sensor_name VARCHAR(50),

    measured_value DECIMAL(12,3),

    severity VARCHAR(30),

    alarm_message TEXT,

    equipment_id VARCHAR(50),

    occurred_at DATETIME(3) NOT NULL,

    resolved_at DATETIME(3),

    duration_sec INT,

    action_status VARCHAR(30) NOT NULL DEFAULT 'DETECTED',

    action_note TEXT,

    action_user_id INT,

    action_time DATETIME,

    acknowledged_by VARCHAR(30),

    source_type VARCHAR(20) NOT NULL DEFAULT 'RULE_DETECTED',

    CONSTRAINT fk_anomaly_batch
        FOREIGN KEY (batch_id)
        REFERENCES batches(batch_id),

    CONSTRAINT fk_anomaly_pouch
        FOREIGN KEY (pouch_id)
        REFERENCES filling_packaging(pouch_id),

    CONSTRAINT fk_anomaly_rule
        FOREIGN KEY (rule_id)
        REFERENCES anomaly_rule(rule_id),

    CONSTRAINT fk_anomaly_user
        FOREIGN KEY (action_user_id)
        REFERENCES users(user_id),

    INDEX idx_anomaly_batch_time
        (batch_id, occurred_at),

    INDEX idx_anomaly_status
        (action_status)

);


-- =====================================================
-- 10. 제조데이터 변경이력 테이블
-- =====================================================

CREATE TABLE data_change_log (

    change_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    table_name VARCHAR(100) NOT NULL,

    record_id VARCHAR(100) NOT NULL,

    column_name VARCHAR(100),

    old_value TEXT,

    new_value TEXT,

    change_type VARCHAR(20) NOT NULL,

    user_id INT NOT NULL,

    change_reason TEXT,

    changed_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_change_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id),

    INDEX idx_change_record
        (table_name, record_id)

);


-- =====================================================
-- 생성 확인
-- =====================================================

SHOW TABLES;