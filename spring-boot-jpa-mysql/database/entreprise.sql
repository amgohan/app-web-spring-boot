--
-- Table structure for table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) CHARACTER SET utf8 NOT NULL,
  `libelle` varchar(150) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `level` (`level`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `first_name`, `last_name`, `birthday`, `email`, `level`) VALUES
(1, 'George', 'Truman', '2000-09-12', 'george.truman@school.com', 10),
(2, 'Gerald', 'Grant', '2003-11-10', 'gerald.grant@school.com', 3),
(3, 'Chester', 'Roosevelt', '1997-05-18', 'chester.roosevelt@school.com', 4),
(4, 'Herbert', 'Truman', '1998-07-15', 'herbert.truman@school.com', 4),
(5, 'Benjamin', 'Wilson', '1998-09-21', 'benjamin.wilson@school.com', 5),
(6, 'Theodore', 'Carter', '1999-09-16', 'theodore.carter@school.com', 4),
(7, 'Harry', 'Harrison', '2003-02-26', 'harry.harrison@school.com', 1),
(8, 'Andrew', 'Adams', '1997-02-08', 'andrew.adams@school.com', 1),
(9, 'Dwight', 'Garfield', '2001-10-03', 'dwight.garfield@school.com', 9),
(10, 'Franklin', 'Kennedy', '2000-10-20', 'franklin.kennedy@school.com', 4),
(11, 'Andrew', 'McKinley', '1997-07-22', 'andrew.mckinley@school.com', 9),
(12, 'Ulysses', 'Johnson', '2001-07-26', 'ulysses.johnson@school.com', 10),
(13, 'Lyndon', 'Truman', '1998-12-17', 'lyndon.truman@school.com', 5),
(14, 'Richard', 'Coolidge', '2000-08-13', 'richard.coolidge@school.com', 1),
(15, 'Bill', 'Eisenhower', '2000-05-25', 'bill.eisenhower@school.com', 7),
(16, 'Bill', 'Arthur', '1998-12-17', 'bill.arthur@school.com', 3),
(17, 'Dwight', 'Carter', '1997-05-15', 'dwight.carter@school.com', 3),
(18, 'Theodore', 'Adams', '1998-06-16', 'theodore.adams@school.com', 6),
(19, 'Dwight', 'Hayes', '1997-01-31', 'dwight.hayes@school.com', 5),
(20, 'William', 'Fillmore', '2004-10-11', 'william.fillmore@school.com', 8),
(21, 'Rutherford', 'Quincy', '2003-10-11', 'rutherford.quincy@school.com', 2),
(22, 'Thomas', 'Hayes', '2000-06-04', 'thomas.hayes@school.com', 9),
(23, 'Ulysses', 'Adams', '1999-12-10', 'ulysses.adams@school.com', 5),
(24, 'Lyndon', 'Adams', '2001-11-30', 'lyndon.adams@school.com', 10),
(25, 'Woodrow', 'Clinton', '2002-07-15', 'woodrow.clinton@school.com', 9),
(26, 'Ronald', 'Adams', '1997-05-08', 'ronald.adams@school.com', 6),
(27, 'Lyndon', 'Harrison', '2003-06-16', 'lyndon.harrison@school.com', 3),
(28, 'Bill', 'Hoover', '2002-07-02', 'bill.hoover@school.com', 3),
(29, 'Zachary', 'Jackson', '1997-04-04', 'zachary.jackson@school.com', 5),
(30, 'Chester', 'Polk', '2003-07-22', 'chester.polk@school.com', 7),
(31, 'Gerald', 'Jefferson', '1997-06-12', 'gerald.jefferson@school.com', 1),
(32, 'Calvin', 'Hoover', '2004-09-05', 'calvin.hoover@school.com', 5),
(33, 'William', 'Clinton', '2000-05-26', 'william.clinton@school.com', 8),
(34, 'Ronald', 'Arthur', '2000-08-04', 'ronald.arthur@school.com', 3),
(35, 'Benjamin', 'Buchanan', '2002-02-05', 'benjamin.buchanan@school.com', 4),
(36, 'Gerald', 'Taylor', '2000-04-01', 'gerald.taylor@school.com', 7),
(37, 'Ronald', 'Johnson', '1998-10-27', 'ronald.johnson@school.com', 2),
(38, 'Woodrow', 'Reagan', '1997-11-05', 'woodrow.reagan@school.com', 10),
(39, 'Ronald', 'Taft', '2000-12-17', 'ronald.taft@school.com', 4),
(40, 'John', 'Polk', '1997-03-30', 'john.polk@school.com', 2),
(41, 'Martin', 'Monroe', '2003-02-25', 'martin.monroe@school.com', 3),
(42, 'Rutherford', 'Reagan', '1998-01-18', 'rutherford.reagan@school.com', 10),
(43, 'Theodore', 'Fillmore', '1999-10-16', 'theodore.fillmore@school.com', 4),
(44, 'Andrew', 'Garfield', '1997-05-25', 'andrew.garfield@school.com', 4),
(45, 'Bill', 'Clinton', '2001-03-01', 'bill.clinton@school.com', 3),
(46, 'Herbert', 'Monroe', '1999-09-19', 'herbert.monroe@school.com', 3),
(47, 'Rutherford', 'Hayes', '1999-05-06', 'rutherford.hayes@school.com', 9),
(48, 'Chester', 'Quincy', '2004-07-10', 'chester.quincy@school.com', 6),
(49, 'Bill', 'Wilson', '2002-12-18', 'bill.wilson@school.com', 3),
(50, 'George', 'Arthur', '2000-06-25', 'george.arthur@school.com', 6),
(51, 'Dwight', 'Carter', '1998-01-24', 'dwight.carter@school.com', 2),
(52, 'Gerald', 'Lincoln', '2003-10-27', 'gerald.lincoln@school.com', 7),
(53, 'John', 'Pierce', '1998-02-27', 'john.pierce@school.com', 1),
(54, 'Rutherford', 'Harrison', '1997-05-01', 'rutherford.harrison@school.com', 4),
(55, 'Herbert', 'Harrison', '2004-08-30', 'herbert.harrison@school.com', 5),
(56, 'Benjamin', 'Buchanan', '2004-01-14', 'benjamin.buchanan@school.com', 5),
(57, 'Rutherford', 'Pierce', '2004-11-17', 'rutherford.pierce@school.com', 7),
(58, 'Bill', 'Polk', '2003-09-30', 'bill.polk@school.com', 5),
(59, 'Warren', 'Cleveland', '2000-08-19', 'warren.cleveland@school.com', 8),
(60, 'Martin', 'Johnson', '2002-08-04', 'martin.johnson@school.com', 6),
(61, 'Rutherford', 'Nixon', '1998-10-05', 'rutherford.nixon@school.com', 8),
(62, 'Martin', 'Reagan', '1999-10-07', 'martin.reagan@school.com', 3),
(63, 'George', 'Madison', '2000-08-17', 'george.madison@school.com', 1),
(64, 'Ulysses', 'Madison', '1997-07-26', 'ulysses.madison@school.com', 1),
(65, 'Harry', 'Wilson', '1997-10-04', 'harry.wilson@school.com', 4),
(66, 'Gerald', 'Monroe', '2002-12-06', 'gerald.monroe@school.com', 2),
(67, 'Gerald', 'Arthur', '1998-10-18', 'gerald.arthur@school.com', 6),
(68, 'Dwight', 'Taylor', '2002-08-14', 'dwight.taylor@school.com', 5),
(69, 'Millard', 'Coolidge', '2002-12-09', 'millard.coolidge@school.com', 6),
(70, 'Jimmy', 'Ford', '2000-03-01', 'jimmy.ford@school.com', 1),
(71, 'Andrew', 'Truman', '2004-12-24', 'andrew.truman@school.com', 3),
(72, 'Grover', 'Johnson', '2004-10-22', 'grover.johnson@school.com', 2),
(73, 'Jimmy', 'Nixon', '1999-02-24', 'jimmy.nixon@school.com', 7),
(74, 'Harry', 'Nixon', '2000-07-12', 'harry.nixon@school.com', 10),
(75, 'Zachary', 'Monroe', '1998-11-10', 'zachary.monroe@school.com', 1),
(76, 'John', 'Roosevelt', '1997-04-27', 'john.roosevelt@school.com', 10),
(77, 'Calvin', 'Hayes', '2003-07-30', 'calvin.hayes@school.com', 7),
(78, 'Rutherford', 'Kennedy', '2004-11-28', 'rutherford.kennedy@school.com', 5),
(79, 'Andrew', 'Reagan', '1997-12-10', 'andrew.reagan@school.com', 4),
(80, 'Benjamin', 'Grant', '2003-01-25', 'benjamin.grant@school.com', 8),
(81, 'Rutherford', 'Madison', '1998-04-01', 'rutherford.madison@school.com', 3),
(82, 'Rutherford', 'Johnson', '2000-08-08', 'rutherford.johnson@school.com', 6),
(83, 'Ronald', 'Washington', '2003-07-16', 'ronald.washington@school.com', 2),
(84, 'Chester', 'Johnson', '1997-04-12', 'chester.johnson@school.com', 4),
(85, 'Harry', 'Carter', '1998-05-25', 'harry.carter@school.com', 7),
(86, 'Jimmy', 'Johnson', '2000-10-15', 'jimmy.johnson@school.com', 2),
(87, 'Herbert', 'Lincoln', '2004-08-02', 'herbert.lincoln@school.com', 3),
(88, 'Bill', 'Roosevelt', '2004-11-21', 'bill.roosevelt@school.com', 1),
(89, 'Thomas', 'Tyler', '2004-10-10', 'thomas.tyler@school.com', 3),
(90, 'Franklin', 'Wilson', '2004-12-10', 'franklin.wilson@school.com', 6),
(91, 'Franklin', 'Ford', '2003-05-24', 'franklin.ford@school.com', 4),
(92, 'Jimmy', 'Kennedy', '2000-12-05', 'jimmy.kennedy@school.com', 6),
(93, 'Calvin', 'Harrison', '2003-11-10', 'calvin.harrison@school.com', 1),
(94, 'Woodrow', 'Monroe', '2004-02-22', 'woodrow.monroe@school.com', 7),
(95, 'Gerald', 'Hoover', '2003-04-19', 'gerald.hoover@school.com', 3),
(96, 'Franklin', 'Ford', '2001-09-29', 'franklin.ford@school.com', 3),
(97, 'James', 'Quincy', '2003-12-03', 'james.quincy@school.com', 10),
(98, 'Herbert', 'Arthur', '2003-04-02', 'herbert.arthur@school.com', 5),
(99, 'Chester', 'Jefferson', '2002-03-22', 'chester.jefferson@school.com', 4),
(100, 'Harry', 'Adams', '1999-03-12', 'harry.adams@school.com', 6);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `last_modified`) VALUES
(1, 'Demo', 'Demo', 'demo', '$2a$04$jH1WY7nMxcZmZ6agYHGYFuY8Kw9KOuCJPLEmJHJeE5/f7lKOKa5y.', 'demo@entreprise.com', 1, '2014-11-03 15:00:24');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  UNIQUE KEY `user_role` (`user_id`,`role_id`),
  KEY `fk_user_role_role` (`role_id`),
  KEY `fk_user_role_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `fk_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `fk_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
