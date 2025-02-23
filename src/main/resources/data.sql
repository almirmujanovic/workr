--  Create Database (Run this manually in SSMS before running the app)
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'job_application_system')
BEGIN
    CREATE DATABASE job_application_system;
END
GO
USE job_application_system;
GO

--  Drop Tables if they exist (optional, for a clean setup)
DROP TABLE IF EXISTS application;
DROP TABLE IF EXISTS job;
DROP TABLE IF EXISTS users;
GO

--  Create Users Table
CREATE TABLE users (
                       id BIGINT IDENTITY(1,1) PRIMARY KEY,
                       username NVARCHAR(50) UNIQUE NOT NULL,
                       password NVARCHAR(255) NOT NULL,
                       role NVARCHAR(10) NOT NULL CHECK (role IN ('USER', 'ADMIN'))
);
GO

-- Create Jobs Table
CREATE TABLE job (
                     id BIGINT IDENTITY(1,1) PRIMARY KEY,
                     title NVARCHAR(100) NOT NULL,
                     description NVARCHAR(500) NOT NULL
);
GO

-- Create Applications Table
CREATE TABLE application (
                             id BIGINT IDENTITY(1,1) PRIMARY KEY,
                             user_id BIGINT NOT NULL,
                             job_id BIGINT NOT NULL,
                             application_text NVARCHAR(1000) NOT NULL,
                             FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                             FOREIGN KEY (job_id) REFERENCES job(id) ON DELETE CASCADE
);
GO

-- Insert Default Users (admin & normal user)
INSERT INTO users (username, password, role) VALUES
('admin', '$2a$10$RTWmbrFtJwBorJruAq9Bc.q0/vFgoroh/f/w0bI6A5COmKOVpumfC', 'ADMIN'), --hash za 'admin'
('user', '$2a$10$YqFaIxSHWy3ciHYBX8YXuu6hSy12KeDKS66UEjpRSbx40GpckuNui', 'USER'); --hash za 'user'
GO

-- Insert Sample Jobs
INSERT INTO job (title, description) VALUES
('Software Developer', 'Looking for a Java developer with Spring experience.'),
('Data Analyst', 'Seeking an analyst proficient in SQL and Python.');
GO
