-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 25, 2023 at 11:56 AM
-- Server version: 10.5.16-MariaDB
-- PHP Version: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id20478454_liujobfinder`
--
CREATE DATABASE IF NOT EXISTS `id20478454_liujobfinder` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `id20478454_liujobfinder`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `Email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `Email`, `password`) VALUES
(1, 'LiuJobFinderAdmin@gmail.com', 'LiuJobFinderAdmin372');

-- --------------------------------------------------------

--
-- Table structure for table `Applied_job`
--

CREATE TABLE `Applied_job` (
  `std_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Applied_job`
--

INSERT INTO `Applied_job` (`std_id`, `job_id`) VALUES
(1002, 19),
(1002, 24),
(1010, 24),
(1012, 25),
(1014, 26);

-- --------------------------------------------------------

--
-- Table structure for table `Business`
--

CREATE TABLE `Business` (
  `Business_id` int(11) NOT NULL,
  `Business_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Business_Email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Business_Size` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Business_Type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Business_Location` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Business_website` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Business`
--

INSERT INTO `Business` (`Business_id`, `Business_name`, `Business_Email`, `password`, `Business_Size`, `Business_Type`, `Business_Location`, `Business_website`) VALUES
(100000, 'Liu university', 'liu@gmail.com', 'LIU2018@blockB', 'Large', 'profit', 'All over Lebanon', 'www.liu.edu.lb'),
(100001, 'Ah', 'aa', 'dg', 'Sole Proprietorship', 'Micro:less than 10 employee', 'sbhf', 'ej'),
(100002, 'Olx Lebanon ', 'OlxLebanon@gmail.com', 'olx232olx', 'Sole Proprietorship', 'Large:250 or more employees', 'Lebanon Beriut zahle ', 'www.olxlbeanon.com'),
(100003, 'Murex', 'Murexhr@gmail.com', '12345M', 'Sole Proprietorship', 'Large:250 or more employees', 'Lebanon,Beriut, Downtown ', 'www.murexCompany.com'),
(100004, 'SeFactory', 'SEhr@gmail.com', '12345', 'Limited Liability Company (LLC)', 'Large:250 or more employees', 'Beriut, Lebanon ', 'www.Sefactory.io');

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `image` varchar(350) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`image`) VALUES
('1947950336_1683378949.jpeg'),
('1496238889_1683379503.jpeg'),
('223605370_1683791546.jpeg'),
('1213001214_1683791566.jpeg'),
('350671790_1683791615.jpeg'),
('119931990_1683791649.jpeg'),
('1625510297_1683791670.jpeg'),
('144349627_1683791713.jpeg'),
('601924634_1683799263.jpeg'),
('2074953128_1683799346.jpeg'),
('150767558_1683799457.jpeg'),
('9370663_1683799472.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `Instructor`
--

CREATE TABLE `Instructor` (
  `Instructor_id` int(11) NOT NULL,
  `First_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Last_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Instructort_Email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Major` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Campus` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Account_Time_Created` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Instructor`
--

INSERT INTO `Instructor` (`Instructor_id`, `First_name`, `Last_name`, `Instructort_Email`, `Password`, `Major`, `Campus`, `Account_Time_Created`) VALUES
(2, 'Haissam', 'Awar', 'Haissam.awar@liu.edu.lb', 'HaissamAwar3232', 'Computer Science', 'Bekaa', '2023-04-14 23:01:54'),
(4, 'garo', 'plwjian', 'garopilwajan', 'aah', 'Computer Science', 'Tyre', '2023-04-14 23:11:54'),
(5, 'garo', 'piljwaan', 'garo', 'garo', 'Computer Science', 'Beriut', '2023-04-29 12:46:38');

-- --------------------------------------------------------

--
-- Table structure for table `Interview_Appointment`
--

CREATE TABLE `Interview_Appointment` (
  `std_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  `bus_id` int(11) NOT NULL,
  `Interview_location` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `interview_DateAndTime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `interview_status` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Interview_Appointment`
--

INSERT INTO `Interview_Appointment` (`std_id`, `job_id`, `bus_id`, `Interview_location`, `interview_DateAndTime`, `interview_status`) VALUES
(1010, 24, 100003, 'Murex (Mar Elias bldg),floor8 Room H1', 'Thursday June 8 at 9:00 am', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `Job`
--

CREATE TABLE `Job` (
  `job_id` int(11) NOT NULL,
  `job_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `job_location` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `job_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `workplace_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `job_requirements` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `job_description` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` enum('Pending','Accepted','Rejected') COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `buss_id` int(11) NOT NULL,
  `Domain` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Job`
--

INSERT INTO `Job` (`job_id`, `job_name`, `job_location`, `job_type`, `workplace_type`, `job_requirements`, `job_description`, `status`, `buss_id`, `Domain`) VALUES
(1, 'Java instructor ', 'Lebanon,Bekaa', 'Full Time', 'on-site', '3 years experience in teaching java\r\nBachelor\'s Degree in Computer Science \r\nMaster degree in any related field \r\nperfect English Skills\r\nLive in west Beqaa or middle Beqaa\r\n\r\n', 'This job wants you to teach first year computer science student the introduction to programming using java', 'Accepted', 100000, 'Computer Science'),
(4, 'Android developer java', 'Beriut ', 'Full Time', 'on-site', 'development Android ', 'dev android dev', 'Accepted', 100001, NULL),
(9, 'marketing manager ', 'Beriut', 'Full Time', 'on-site', 'Bs in marketing\n4 years of experience\ngood English Arabic skills\nlive near beriut', 'you are responsible for marketing for our app using the most modern ways of marketing and developing the sales side on our app', 'Accepted', 100002, NULL),
(11, 'database administrator ', 'Lebanon zahle ', 'Full Time', 'on-site', 'sbshhs', 'hhshshss', 'Rejected', 100002, NULL),
(18, 'senior software engineer ', 'Lebanon bekaa barlias', 'Part Time', 'on-line', 'Bs in computer science or related field\n2 years experience in both web and mobile development ', 'you are responsible for developing softwares such as web or mobile devices', 'Rejected', 100001, 'Computer Science'),
(19, 'database administrator ', 'Beriut Lebanon ', 'Full Time', 'on-site', 'Masters in database design\n5 years experience \nlive near beriut ', 'you are responsible to be the admin of the olx Lebanon applications databases for managing and maintaining issues ', 'Accepted', 100002, 'Computer Science'),
(20, 'python developer ', 'Beriut zahle ', 'Full Time', 'on-site', '3 years experience in python\nBs in computer science ', 'this job is for developing python scripting over web ', 'Accepted', 100002, 'Computer Science'),
(21, 'Aya she', 'zahle ', 'Full Time', 'on-site', 'shbshs', 'hshsjs', 'Rejected', 100002, 'Computer Science'),
(22, 'Java programming Teacher ', 'zahle ', 'Full Time', 'on-site', 'sbshsh', 'hshshs', 'Rejected', 100001, 'Computer Science'),
(24, 'Ai engineer ', 'New York City ,United state', 'Full Time', 'on-site', 'Ms in AI engineering.\nBs in computer science.\nCan work outside his region.\nready to travel Every Year more than 10 Times.\n', 'We are seeking a highly skilled and motivated Ai engineer with huge experience in building Ai systems and robots and have newly ideas in developing AI and using Ai in teaching in schools and using Ai in jobs.develop intelligent algorithms capable of learning, analysing and predicting future events. Their role is to create machines capable of reasoning like the human brain,analyses the functioning of the human brain in order to build computer programs with the same cognitive abilities as humans,specialise in different areas such as Machine Learning or Deep Learning, which are derived from AI. Machine Learning is based on algorithms and decision trees, while Deep Learning is based on neural networks.', 'Accepted', 100003, 'Computer Science'),
(25, 'Assembly developer ', 'Beriut Lebanon ', 'Part Time', 'on-site', 'Bs in computer science Computer engineering or any related Field\n2-4 year\'s experience\nHave a passport\ngood English and French skills', 'write a program that directly communicates with the computer memory and the register of any device. Though, to write quality code, the developers need to think through an excellent algorith', 'Accepted', 100003, 'Computer Science'),
(26, 'Marketing Manager ', 'Amman Jordan ', 'Full Time', 'Hybrid', 'Ms in Business marketing or any related field\n7-9 experience years in this field.\nPowerful in English ', 'you are responsible to develop the marketing ways in the Company by developing The sales persons in more advanced ways In sale, and you are responsible for communication between The customers and other Company that\'s put ads in our company ', 'Accepted', 100003, 'Business Marketing'),
(30, 'hshs', 'hsbs', 'Full Time', 'on-site', 'nsbs', 'bzbs', 'Pending', 100003, 'Computer Science');

-- --------------------------------------------------------

--
-- Table structure for table `Resources`
--

CREATE TABLE `Resources` (
  `Resource_Major` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Resource_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Resource_Type` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `res_content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Resources`
--

INSERT INTO `Resources` (`Resource_Major`, `Resource_name`, `Resource_Type`, `res_content`) VALUES
('Computer Science', 'Algorthims', 'Link', 'https://www.youtube.com/playlist?list=PLCInYL3l2AajqOUW_2SwjWeMwf4vL4RSp');

-- --------------------------------------------------------

--
-- Table structure for table `Student`
--

CREATE TABLE `Student` (
  `Student_id` int(11) NOT NULL,
  `First_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Last_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Student_Email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Major` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Campus` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Account_Time_Created` datetime DEFAULT current_timestamp(),
  `Res_Major` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Student`
--

INSERT INTO `Student` (`Student_id`, `First_name`, `Last_name`, `Student_Email`, `Password`, `Major`, `Campus`, `Account_Time_Created`, `Res_Major`) VALUES
(1000, 'Ahmad', 'Azimeh', '21930467@students.liu.edu.lb', 'Ahmadazimeh372', 'Computer Science', 'Bekaa', '2023-04-12 22:18:24', 'Computer Science'),
(1002, 'Osama', 'Kayed', '22030213@students.liu.edu.lb', 'Osama123', 'Computer Science', 'Beriut', '2023-04-13 00:58:23', NULL),
(1003, 'Ali', 'Ali', '22030214@students.liu.edu.lb', 'Ahmad', 'Biology', 'Tyre', '2023-04-13 01:02:33', NULL),
(1004, 'asmaa', 'sawa', '22230213@students.liu.edu.lb', 'asmaa123', 'physics', 'Bekaa', '2023-04-13 01:03:38', NULL),
(1005, 'nour', 'siage', '21821821@students.liu.edu.lb', 'nour', 'Computer Science', 'Bekaa', '2023-04-13 01:05:52', NULL),
(1008, 'Khaled', 'Khalil ', '22233222@students.liu.edu.lb', 'khaled123', 'physics', 'Beriut', '2023-04-13 10:15:49', NULL),
(1009, 'Ahmad', 'Ahmm', '21830999@students.liu.edu.lb', 'ahh', 'Computer Science', 'Bekaa', '2023-04-13 11:30:47', NULL),
(1010, 'mohamad', 'chahine', '22030049@students.liu.edu.lb', '123456789Mc', 'Computer Science', 'Bekaa', '2023-04-18 08:27:40', NULL),
(1011, 'mohamad ', 'lahib', '34553345@students.liu.edu.lb', 'mohammad@123', 'Computer Science', 'Bekaa', '2023-04-21 21:01:21', NULL),
(1012, 'Ali', 'othman', '21921921@students.liu.edu.lb', 'Ahmadaz123', 'Computer Science', 'Beriut', '2023-05-07 16:22:56', NULL),
(1013, 'Youssef', 'Houssein', '22130776@students.liu.edu.lb', 'Youss123', 'Computer Science', 'Bekaa', '2023-05-09 07:35:32', NULL),
(1014, 'Alice ', 'toume', '22230020@students.liu.edu.lb', '12345A', 'Business Marketing', 'Tyre', '2023-05-14 11:58:57', NULL),
(1015, 'Cyrine', 'AbouNouh', '22030427@students.liu.edu.lb', '123', 'Computer Science', 'Bekaa', '2023-05-23 07:25:33', NULL),
(1016, 'khaled', 'Azimeh', '22022022@students.liu.edu.lb', '12345', 'Computer Science', 'Bekaa', '2023-05-23 11:59:03', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `Student_Profile`
--

CREATE TABLE `Student_Profile` (
  `Personal_Email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Education` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Location` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Experience` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Std_id` int(11) DEFAULT NULL,
  `Image` varchar(350) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Student_Profile`
--

INSERT INTO `Student_Profile` (`Personal_Email`, `Education`, `Location`, `Experience`, `Std_id`, `Image`) VALUES
('bsbs', 'dbdb', 'dndn', 'snsn', 1012, '1965290988_1683478958.jpeg'),
('khaledaz123@gmail.com', '.', ' .', '.', 1016, '108344489_1684843286.jpeg'),
('mhmd@gmail.com', '', '', '', 1010, '560331728_1684643373.jpeg'),
('Names', 'Nameccv', 'Namevvv', 'Namecv', 1005, '1045488563_1683445322.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`) VALUES
(1, 'LiuJobFinderAdmin@gmail.com', 'LiuJobFinderAdmin372'),
(4, 'garopilwajan', 'aah'),
(5, 'garo', 'garo'),
(1002, '22030213@students.liu.edu.lb', 'Osama123'),
(1003, '22030214@students.liu.edu.lb', 'Ahmad'),
(1004, '22230213@students.liu.edu.lb', 'asmaa123'),
(1005, '21821821@students.liu.edu.lb', 'nour'),
(1008, '22233222@students.liu.edu.lb', 'khaled123'),
(1009, '21830999@students.liu.edu.lb', 'ahh'),
(1010, '22030049@students.liu.edu.lb', '123456789Mc'),
(1011, '34553345@students.liu.edu.lb', 'mohammad@123'),
(1012, '21921921@students.liu.edu.lb', 'Ahmadaz123'),
(1013, '22130776@students.liu.edu.lb', 'Youss123'),
(1014, '22230020@students.liu.edu.lb', '12345A'),
(1015, '22030427@students.liu.edu.lb', '123'),
(1016, '22022022@students.liu.edu.lb', '12345'),
(100001, 'aa', 'dg'),
(100002, 'OlxLebanon@gmail.com', 'olx232olx'),
(100003, 'Murexhr@gmail.com', '12345M'),
(100004, 'SEhr@gmail.com', '12345');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Indexes for table `Applied_job`
--
ALTER TABLE `Applied_job`
  ADD PRIMARY KEY (`std_id`,`job_id`),
  ADD KEY `job_id` (`job_id`);

--
-- Indexes for table `Business`
--
ALTER TABLE `Business`
  ADD PRIMARY KEY (`Business_id`),
  ADD UNIQUE KEY `Business_Email` (`Business_Email`);

--
-- Indexes for table `Instructor`
--
ALTER TABLE `Instructor`
  ADD PRIMARY KEY (`Instructor_id`),
  ADD UNIQUE KEY `Instructort_Email` (`Instructort_Email`);

--
-- Indexes for table `Interview_Appointment`
--
ALTER TABLE `Interview_Appointment`
  ADD PRIMARY KEY (`std_id`,`job_id`,`bus_id`),
  ADD KEY `job_id` (`job_id`),
  ADD KEY `bus_id` (`bus_id`);

--
-- Indexes for table `Job`
--
ALTER TABLE `Job`
  ADD PRIMARY KEY (`job_id`),
  ADD KEY `buss_id` (`buss_id`);

--
-- Indexes for table `Resources`
--
ALTER TABLE `Resources`
  ADD PRIMARY KEY (`Resource_Major`);

--
-- Indexes for table `Student`
--
ALTER TABLE `Student`
  ADD PRIMARY KEY (`Student_id`),
  ADD UNIQUE KEY `Student_Email` (`Student_Email`),
  ADD KEY `Res_Major` (`Res_Major`);

--
-- Indexes for table `Student_Profile`
--
ALTER TABLE `Student_Profile`
  ADD PRIMARY KEY (`Personal_Email`),
  ADD KEY `Std_id` (`Std_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Business`
--
ALTER TABLE `Business`
  MODIFY `Business_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100005;

--
-- AUTO_INCREMENT for table `Instructor`
--
ALTER TABLE `Instructor`
  MODIFY `Instructor_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `Job`
--
ALTER TABLE `Job`
  MODIFY `job_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `Student`
--
ALTER TABLE `Student`
  MODIFY `Student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1017;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Applied_job`
--
ALTER TABLE `Applied_job`
  ADD CONSTRAINT `Applied_job_ibfk_1` FOREIGN KEY (`std_id`) REFERENCES `Student` (`Student_id`),
  ADD CONSTRAINT `Applied_job_ibfk_2` FOREIGN KEY (`job_id`) REFERENCES `Job` (`job_id`);

--
-- Constraints for table `Interview_Appointment`
--
ALTER TABLE `Interview_Appointment`
  ADD CONSTRAINT `Interview_Appointment_ibfk_1` FOREIGN KEY (`std_id`) REFERENCES `Student` (`Student_id`),
  ADD CONSTRAINT `Interview_Appointment_ibfk_2` FOREIGN KEY (`job_id`) REFERENCES `Job` (`job_id`),
  ADD CONSTRAINT `Interview_Appointment_ibfk_3` FOREIGN KEY (`bus_id`) REFERENCES `Business` (`Business_id`);

--
-- Constraints for table `Job`
--
ALTER TABLE `Job`
  ADD CONSTRAINT `Job_ibfk_1` FOREIGN KEY (`buss_id`) REFERENCES `Business` (`Business_id`);

--
-- Constraints for table `Student`
--
ALTER TABLE `Student`
  ADD CONSTRAINT `Student_ibfk_1` FOREIGN KEY (`Res_Major`) REFERENCES `Resources` (`Resource_Major`);

--
-- Constraints for table `Student_Profile`
--
ALTER TABLE `Student_Profile`
  ADD CONSTRAINT `Student_Profile_ibfk_1` FOREIGN KEY (`Std_id`) REFERENCES `Student` (`Student_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
