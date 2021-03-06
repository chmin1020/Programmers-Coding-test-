-- 코드를 입력하세요
SELECT * FROM ANIMAL_INS ORDER BY ANIMAL_ID

-- 코드를 입력하세요
SELECT NAME, DATETIME FROM ANIMAL_INS ORDER BY ANIMAL_ID DESC

-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME FROM ANIMAL_INS WHERE INTAKE_CONDITION = 'Sick' ORDER BY ANIMAL_ID;

-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME FROM ANIMAL_INS WHERE INTAKE_CONDITION != 'Aged';

-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME FROM ANIMAL_INS ORDER BY ANIMAL_ID

-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME, DATETIME FROM ANIMAL_INS ORDER BY NAME ASC, DATETIME DESC

-- 코드를 입력하세요
SELECT NAME FROM ANIMAL_INS WHERE DATETIME = (SELECT MIN(DATETIME) FROM ANIMAL_INS)

-- 코드를 입력하세요
SELECT ANIMAL_ID FROM ANIMAL_INS WHERE NAME is NULL ORDER BY ANIMAL_ID

-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME, SEX_UPON_INTAKE FROM ANIMAL_INS 
WHERE (NAME='Lucy' or NAME='Ella' or NAME='Pickle' or NAME='Rogan' or NAME='Sabrina' or NAME='Mitty') ORDER BY ANIMAL_ID

-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME FROM ANIMAL_INS WHERE UPPER(NAME) LIKE '%EL%' AND ANIMAL_TYPE='Dog' ORDER BY NAME

-- 코드를 입력하세요
SELECT ANIMAL_TYPE, IFNULL(NAME, "No name"), SEX_UPON_INTAKE FROM ANIMAL_INS ORDER BY ANIMAL_ID

-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME, CASE
WHEN SEX_UPON_INTAKE LIKE 'Neutered%' THEN 'O'
WHEN SEX_UPON_INTAKE LIKE 'Spayed%' THEN 'O'
ELSE 'X'
END AS '중성화' FROM ANIMAL_INS ORDER BY ANIMAL_ID

-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME, date_format(DATETIME,"%Y-%m-%d") AS '날짜' FROM ANIMAL_INS ORDER BY ANIMAL_ID
