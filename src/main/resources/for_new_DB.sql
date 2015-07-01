#for postgresql
CREATE SCHEMA univ_schema
CREATE TABLE univ_schema.student (
    student_id serial PRIMARY KEY
  , student_name    text NOT NULL

);

CREATE TABLE univ_schema.course (
    course_id  serial PRIMARY KEY
  , course_name     text NOT NULL

);

CREATE TABLE univ_schema.course_student (
    course_student_id serial PRIMARY KEY
  , reg_date date NOT NULL
  , course_id    int REFERENCES univ_schema.course (course_id) ON UPDATE CASCADE
  , student_id int REFERENCES univ_schema.student (student_id) ON UPDATE CASCADE

);
