-- day05 / TestEntity sample, SQL 카멜표기법 대신에 _(언더바) 
insert into test( name, descri , price , create_Date, update_Time)
    VALUE('코카콜라1' , '맛있는 탄산음료1', 1000, now(), now()),
    ('사이다1' , '맛있는 탄산음료2', 1600, now(), now()),
    ('환타' , '맛있는 탄산음료3', 700, now(), now());


