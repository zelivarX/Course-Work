CREATE TABLE `students` (
	`fakulteten_nomer` INT PRIMARY KEY,
	`EGN` INT UNIQUE NOT NULL,
	`first_name` VARCHAR(25) NOT NULL,
	`last_name` VARCHAR(25) NOT NULL,
	`username` VARCHAR(25) UNIQUE NOT NULL,
	`pass` VARCHAR(25) NOT NULL,
	`semester` TINYINT NOT NULL DEFAULT 1,
	`age` TINYINT not null,
	`sex` VARCHAR(6)
);

CREATE TABLE `school_subjects` (
	`subject_id` INT PRIMARY KEY,
	`subject_name` VARCHAR(25) NOT NULL
);

CREATE TABLE `student_attends` (
	`student_fakulteten_nomer` INT NOT NULL,
	`school_subject_id` INT NOT NULL,
	UNIQUE(`student_fakulteten_nomer`, `school_subject_id`),
	FOREIGN KEY (`student_fakulteten_nomer`) references `students`(`fakulteten_nomer`)
		on delete cascade on update cascade,
	FOREIGN KEY (`school_subject_id`) references `school_subjects`(`subject_id`)
		on delete cascade on update cascade
	
);

CREATE TABLE `grades` (
	`grade` TINYINT NOT NULL,
	`student_fakulteten_nomer` INT NOT NULL,
	`school_subject_id` INT NOT NULL,
	FOREIGN KEY (`student_fakulteten_nomer`) references `students`(`fakulteten_nomer`)
		on delete cascade on update cascade,
	FOREIGN KEY (`school_subject_id`) references `school_subjects`(`subject_id`)
		on delete cascade on update cascade
);
