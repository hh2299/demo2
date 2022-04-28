/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : talent

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 28/04/2022 18:44:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for applicant
-- ----------------------------
DROP TABLE IF EXISTS `applicant`;
CREATE TABLE `applicant` (
  `id` bigint NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `nation` varchar(50) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `graduate_school` varchar(255) DEFAULT NULL,
  `is_magor_illness` int DEFAULT NULL,
  `disciplinary_history` varchar(255) DEFAULT NULL,
  `intention_position_id` bigint DEFAULT NULL COMMENT '意向工作',
  `intention_position` varchar(255) DEFAULT NULL,
  `award_history` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `self_evaluation` varchar(255) DEFAULT NULL,
  `is_hired` int DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for applicant_cv
-- ----------------------------
DROP TABLE IF EXISTS `applicant_cv`;
CREATE TABLE `applicant_cv` (
  `id` bigint NOT NULL,
  `applicant_id` bigint DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `position_id` bigint DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` bigint DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for company_position
-- ----------------------------
DROP TABLE IF EXISTS `company_position`;
CREATE TABLE `company_position` (
  `id` bigint NOT NULL,
  `company_id` bigint DEFAULT NULL,
  `position_id` bigint DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for hire
-- ----------------------------
DROP TABLE IF EXISTS `hire`;
CREATE TABLE `hire` (
  `id` bigint NOT NULL,
  `company_id` bigint DEFAULT NULL,
  `applicant_id` bigint DEFAULT NULL,
  `recruit_id` bigint DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for hr
-- ----------------------------
DROP TABLE IF EXISTS `hr`;
CREATE TABLE `hr` (
  `id` bigint NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for perform_weight
-- ----------------------------
DROP TABLE IF EXISTS `perform_weight`;
CREATE TABLE `perform_weight` (
  `id` bigint NOT NULL,
  `learn` int DEFAULT NULL,
  `liability` int DEFAULT NULL,
  `social` int DEFAULT NULL,
  `expertise` int DEFAULT NULL,
  `competitive` int DEFAULT NULL,
  `schedule` int DEFAULT NULL,
  `computer` int DEFAULT NULL,
  `manage` int DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for performance
-- ----------------------------
DROP TABLE IF EXISTS `performance`;
CREATE TABLE `performance` (
  `id` bigint NOT NULL,
  `applicant_cv_id` bigint DEFAULT NULL,
  `month` datetime DEFAULT NULL,
  `leader_eval` varchar(255) DEFAULT NULL,
  `self_eval` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL COMMENT '综合评级',
  `learn` int DEFAULT NULL,
  `liability` int DEFAULT NULL,
  `social` int DEFAULT NULL,
  `expertise` int DEFAULT NULL,
  `competitive` int DEFAULT NULL,
  `schedule` int DEFAULT NULL,
  `computer` int DEFAULT NULL,
  `manage` int DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for recruit
-- ----------------------------
DROP TABLE IF EXISTS `recruit`;
CREATE TABLE `recruit` (
  `id` bigint NOT NULL,
  `position_id` bigint DEFAULT NULL,
  `position_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `num` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `salary` decimal(11,2) DEFAULT NULL,
  `requirements` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `weight_id` bigint DEFAULT NULL COMMENT '表现权重id',
  `is_published` int DEFAULT NULL,
  `is_finished` int DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
