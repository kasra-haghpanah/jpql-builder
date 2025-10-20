--DROP PROCEDURE calculate;
--DROP PROCEDURE IF EXISTS calculate;
    DELIMITER //
    create PROCEDURE calculate(
    IN x double precision ,
    IN y double precision ,
    OUT sum double precision)
    BEGIN

    set sum = x + y;
    SELECT x,y,sum from DUAL;
    END //


----------------------------------
CALL calculate(1,2,@xw)
----------------------------------