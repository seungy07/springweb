-- =========================================================
-- Q-FLOW
-- 가상 제조공정 기반 실시간 모니터링 및 품질관리 플랫폼
-- =========================================================

CREATE DATABASE IF NOT EXISTS maskpack_mes;
USE maskpack_mes;

-- =========================================================
-- 1. 사용자 테이블
-- users
-- =========================================================
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    password_hash VARCHAR(255),
    user_name VARCHAR(30),
    role VARCHAR(30),
    is_active BOOLEAN,
    created_at DATETIME,
    updated_at DATETIME
);
-- =========================================================
-- 2. 생산 LOT 테이블
-- batches
-- =========================================================
CREATE TABLE batches (
    batch_id VARCHAR(30) PRIMARY KEY,
    product_code VARCHAR(30),
    product_name VARCHAR(100),
    target_bulk_kg DECIMAL(12,4),
    actual_bulk_kg DECIMAL(12,4),
    target_units INT,
    actual_units INT,
    defect_units INT,
    start_time DATETIME(3),
    end_time DATETIME(3),
    status VARCHAR(30),
    user_id INT,
    tank_id VARCHAR(30),
    record_source VARCHAR(20),
    CONSTRAINT fk_batches_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
);

-- =========================================================
-- 3. 원료 칭량이력 테이블
-- material_dispensing
-- 해당 공정 : ① 원료 칭량
-- =========================================================
CREATE TABLE material_dispensing (
    dispense_id VARCHAR(100) PRIMARY KEY,
    batch_id VARCHAR(30),
    material_code VARCHAR(40),
    material_name VARCHAR(150),
    raw_material_lot VARCHAR(50),
    target_qty_kg DECIMAL(12,4),
    actual_qty_kg DECIMAL(12,4),
    user_id INT,
    dispensed_at DATETIME(3),
    status VARCHAR(50),
    CONSTRAINT fk_dispensing_batch
        FOREIGN KEY (batch_id)
        REFERENCES batches(batch_id),
    CONSTRAINT fk_dispensing_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
);
-- =========================================================
-- 4. 공정 실행이력 테이블
-- process_execution
-- 해당 공정 : ① ~ ⑤ 전체
-- =========================================================
CREATE TABLE process_execution (
    execution_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    batch_id VARCHAR(30),
    process_code VARCHAR(30),
    start_time DATETIME(3),
    end_time DATETIME(3),
    duration_min DECIMAL(10,2),
    status VARCHAR(30),
    record_source VARCHAR(20),
    CONSTRAINT fk_process_batch
        FOREIGN KEY (batch_id)
        REFERENCES batches(batch_id)
);
-- =========================================================
-- 5. 공정 센서 측정이력 테이블
-- sensor_telemetry
-- 해당 공정 : ② 가열·혼합 / ③ 냉각·마무리
-- =========================================================
CREATE TABLE sensor_telemetry (
    sensor_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    execution_id BIGINT,
    `timestamp` DATETIME(3),
    tank_temp_c DECIMAL(8,3),
    paddle_rpm DECIMAL(10,2),
    homomixer_rpm DECIMAL(10,2),
    bulk_viscosity_cps DECIMAL(12,3),
    ph_level DECIMAL(6,3),
    motor_torque_pct DECIMAL(6,2),
    vacuum_kpa DECIMAL(8,3),
    cooling_valve_pct DECIMAL(6,2),
    record_source VARCHAR(20),
    user_id INT,
    CONSTRAINT fk_sensor_execution
        FOREIGN KEY (execution_id)
        REFERENCES process_execution(execution_id),
    CONSTRAINT fk_sensor_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
);
-- =========================================================
-- 6. 벌크 품질검사 테이블
-- bulk_qc
-- 해당 공정 : ④ 벌크 품질검사
-- =========================================================
CREATE TABLE bulk_qc (
    qc_id VARCHAR(50) PRIMARY KEY,
    batch_id VARCHAR(30),
    sample_time DATETIME(3),
    user_id INT,
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
    record_source VARCHAR(20),
    CONSTRAINT fk_bulk_qc_batch
        FOREIGN KEY (batch_id)
        REFERENCES batches(batch_id),
    CONSTRAINT fk_bulk_qc_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
);
-- =========================================================
-- 7. 충진·포장 및 최종검사 테이블
-- filling_packaging
-- 해당 공정 : ⑤ 충진·포장 및 최종검사
-- =========================================================
CREATE TABLE filling_packaging (
    pouch_id VARCHAR(100) PRIMARY KEY,
    batch_id VARCHAR(30),
    user_id INT,
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
    record_source VARCHAR(20),
    CONSTRAINT fk_packaging_batch
        FOREIGN KEY (batch_id)
        REFERENCES batches(batch_id),
    CONSTRAINT fk_packaging_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
);
-- =========================================================
-- 8. 이상 감지 규칙 테이블
-- anomaly_rule
-- =========================================================
CREATE TABLE anomaly_rule (
    rule_id INT AUTO_INCREMENT PRIMARY KEY,
    process_code VARCHAR(30),
    anomaly_type VARCHAR(50),
    sensor_name VARCHAR(50),
    condition_type VARCHAR(20),
    warning_min DECIMAL(12,3),
    warning_max DECIMAL(12,3),
    critical_min DECIMAL(12,3),
    critical_max DECIMAL(12,3),
    status_value VARCHAR(50),
    duration_seconds INT,
    check_items TEXT,
    response_description TEXT,
    is_active BOOLEAN
);
-- =========================================================
-- 9. 이상치 발생 및 조치이력 테이블
-- anomaly_event
-- =========================================================
CREATE TABLE anomaly_event (
    anomaly_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    batch_id VARCHAR(30),
    pouch_id VARCHAR(100),
    rule_id INT,
    source_alarm_id VARCHAR(50) UNIQUE,
    process_code VARCHAR(30),
    anomaly_type VARCHAR(50),
    sensor_name VARCHAR(50),
    measured_value DECIMAL(12,3),
    severity VARCHAR(30),
    alarm_message TEXT,
    occurred_at DATETIME(3),
    resolved_at DATETIME(3),
    duration_sec INT,
    action_status VARCHAR(30),
    action_note TEXT,
    user_id INT,
    action_time DATETIME,
    source_type VARCHAR(20),
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
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
);
-- =========================================================
-- 10. 데이터 변경이력 테이블
-- data_change_log
-- =========================================================
CREATE TABLE data_change_log (
    change_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    table_name VARCHAR(100),
    record_id VARCHAR(100),
    column_name VARCHAR(100),
    old_value TEXT,
    new_value TEXT,
    change_type VARCHAR(20),
    user_id INT,
    change_reason TEXT,
    changed_at DATETIME,
    CONSTRAINT fk_change_log_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
);
-- =========================================================
-- 데이터 조회 성능을 위한 INDEX
-- =========================================================

-- LOT별 원료 칭량이력
CREATE INDEX idx_dispensing_batch
ON material_dispensing(batch_id);


-- LOT별 공정 실행이력
CREATE INDEX idx_process_batch
ON process_execution(batch_id);


-- 공정별 센서 측정시간
CREATE INDEX idx_sensor_execution_timestamp
ON sensor_telemetry(execution_id, `timestamp`);


-- LOT별 벌크 품질검사
CREATE INDEX idx_bulk_qc_batch
ON bulk_qc(batch_id);


-- LOT별 완제품 검사시간
CREATE INDEX idx_packaging_batch_timestamp
ON filling_packaging(batch_id, `timestamp`);


-- LOT별 이상 발생시간
CREATE INDEX idx_anomaly_batch_time
ON anomaly_event(batch_id, occurred_at);


-- =========================================================
-- 생성 결과 확인
-- =========================================================

SHOW TABLES;