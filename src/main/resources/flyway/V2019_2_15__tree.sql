
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tree
-- ----------------------------
DROP TABLE IF EXISTS `tree`;
CREATE TABLE `tree`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parentId`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tree
-- ----------------------------
INSERT INTO `tree` VALUES (1, 'a', 1, 0);
INSERT INTO `tree` VALUES (2, 'aa', 2, 0);
INSERT INTO `tree` VALUES (3, 'a1', 1, 1);
INSERT INTO `tree` VALUES (4, 'b1', 2, 1);
INSERT INTO `tree` VALUES (5, 'ccc', 1, 4);

SET FOREIGN_KEY_CHECKS = 1;
