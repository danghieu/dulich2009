/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : yen

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2009-12-07 00:52:23
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `jena_g1t0_reif`
-- ----------------------------
DROP TABLE IF EXISTS `jena_g1t0_reif`;
CREATE TABLE `jena_g1t0_reif` (
  `Subj` varchar(100) character set utf8 collate utf8_bin default NULL,
  `Prop` varchar(100) character set utf8 collate utf8_bin default NULL,
  `Obj` varchar(100) character set utf8 collate utf8_bin default NULL,
  `GraphID` int(11) default NULL,
  `Stmt` varchar(100) character set utf8 collate utf8_bin NOT NULL,
  `HasType` char(1) NOT NULL,
  UNIQUE KEY `jena_g1t0_reifXSTMT` (`Stmt`,`HasType`),
  KEY `jena_g1t0_reifXSP` (`Subj`,`Prop`),
  KEY `jena_g1t0_reifXO` (`Obj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jena_g1t0_reif
-- ----------------------------

-- ----------------------------
-- Table structure for `jena_g1t1_stmt`
-- ----------------------------
DROP TABLE IF EXISTS `jena_g1t1_stmt`;
CREATE TABLE `jena_g1t1_stmt` (
  `Subj` varchar(100) character set utf8 collate utf8_bin NOT NULL,
  `Prop` varchar(100) character set utf8 collate utf8_bin NOT NULL,
  `Obj` varchar(100) character set utf8 collate utf8_bin NOT NULL,
  `GraphID` int(11) default NULL,
  KEY `jena_g1t1_stmtXSP` (`Subj`,`Prop`),
  KEY `jena_g1t1_stmtXO` (`Obj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jena_g1t1_stmt
-- ----------------------------
INSERT INTO `jena_g1t1_stmt` VALUES ('Uv::http://www.owl-ontologies.com/Travel.owl#yenloi1:', 'Uv::http://www.w3.org/1999/02/22-rdf-syntax-ns#type:', 'Uv::http://www.w3.org/2002/07/owl#Class:', '1');
INSERT INTO `jena_g1t1_stmt` VALUES ('Uv::http://www.owl-ontologies.com/Travel.owl#Address_yenloi1:', 'Uv::http://www.w3.org/1999/02/22-rdf-syntax-ns#type:', 'Uv::http://www.owl-ontologies.com/Travel.owl#yenloi1:', '1');
INSERT INTO `jena_g1t1_stmt` VALUES ('Uv::http://www.owl-ontologies.com/Travel.owl#Address_yenloi1:', 'Uv::http://www.owl-ontologies.com/Travel.owl#country:', 'Lv:0::yenloi1:', '1');
INSERT INTO `jena_g1t1_stmt` VALUES ('Uv::http://www.owl-ontologies.com/Travel.owl#Address_yenloi1:', 'Uv::http://www.owl-ontologies.com/Travel.owl#street:', 'Lv:0::NamDinh:', '1');
INSERT INTO `jena_g1t1_stmt` VALUES ('Uv::http://www.owl-ontologies.com/Travel.owl#Address_yenloi1:', 'Uv::http://www.owl-ontologies.com/Travel.owl#zipCode:', 'Lv:0::085:', '1');
INSERT INTO `jena_g1t1_stmt` VALUES ('Uv::http://www.owl-ontologies.com/Travel.owl#yenloi2:', 'Uv::http://www.w3.org/1999/02/22-rdf-syntax-ns#type:', 'Uv::http://www.w3.org/2002/07/owl#Class:', '1');
INSERT INTO `jena_g1t1_stmt` VALUES ('Uv::http://www.owl-ontologies.com/Travel.owl#Address_yenloi2:', 'Uv::http://www.w3.org/1999/02/22-rdf-syntax-ns#type:', 'Uv::http://www.owl-ontologies.com/Travel.owl#yenloi2:', '1');
INSERT INTO `jena_g1t1_stmt` VALUES ('Uv::http://www.owl-ontologies.com/Travel.owl#Address_yenloi2:', 'Uv::http://www.owl-ontologies.com/Travel.owl#country:', 'Lv:0::yenloi2:', '1');
INSERT INTO `jena_g1t1_stmt` VALUES ('Uv::http://www.owl-ontologies.com/Travel.owl#Address_yenloi2:', 'Uv::http://www.owl-ontologies.com/Travel.owl#street:', 'Lv:0::NamDinh:', '1');
INSERT INTO `jena_g1t1_stmt` VALUES ('Uv::http://www.owl-ontologies.com/Travel.owl#Address_yenloi2:', 'Uv::http://www.owl-ontologies.com/Travel.owl#zipCode:', 'Lv:0::085:', '1');
INSERT INTO `jena_g1t1_stmt` VALUES ('Uv::http://www.owl-ontologies.com/Travel.owl#vietnam:', 'Uv::http://www.w3.org/1999/02/22-rdf-syntax-ns#type:', 'Uv::http://www.w3.org/2002/07/owl#Class:', '1');
INSERT INTO `jena_g1t1_stmt` VALUES ('Uv::http://www.owl-ontologies.com/Travel.owl#Address_vietnam:', 'Uv::http://www.w3.org/1999/02/22-rdf-syntax-ns#type:', 'Uv::http://www.owl-ontologies.com/Travel.owl#vietnam:', '1');
INSERT INTO `jena_g1t1_stmt` VALUES ('Uv::http://www.owl-ontologies.com/Travel.owl#Address_vietnam:', 'Uv::http://www.owl-ontologies.com/Travel.owl#country:', 'Lv:0::vietnam:', '1');
INSERT INTO `jena_g1t1_stmt` VALUES ('Uv::http://www.owl-ontologies.com/Travel.owl#Address_vietnam:', 'Uv::http://www.owl-ontologies.com/Travel.owl#street:', 'Lv:0::NamDinh:', '1');
INSERT INTO `jena_g1t1_stmt` VALUES ('Uv::http://www.owl-ontologies.com/Travel.owl#Address_vietnam:', 'Uv::http://www.owl-ontologies.com/Travel.owl#zipCode:', 'Lv:0::085:', '1');

-- ----------------------------
-- Table structure for `jena_graph`
-- ----------------------------
DROP TABLE IF EXISTS `jena_graph`;
CREATE TABLE `jena_graph` (
  `ID` int(11) NOT NULL auto_increment,
  `Name` tinyblob,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jena_graph
-- ----------------------------
INSERT INTO `jena_graph` VALUES ('1', 0x687474703A2F2F6C6F63616C686F73743A383038302F4D794F6E746F6C6F677920);

-- ----------------------------
-- Table structure for `jena_long_lit`
-- ----------------------------
DROP TABLE IF EXISTS `jena_long_lit`;
CREATE TABLE `jena_long_lit` (
  `ID` int(11) NOT NULL auto_increment,
  `Head` varchar(100) character set utf8 collate utf8_bin NOT NULL,
  `ChkSum` bigint(20) default NULL,
  `Tail` mediumblob,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `jena_XLIT` (`Head`,`ChkSum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jena_long_lit
-- ----------------------------

-- ----------------------------
-- Table structure for `jena_long_uri`
-- ----------------------------
DROP TABLE IF EXISTS `jena_long_uri`;
CREATE TABLE `jena_long_uri` (
  `ID` int(11) NOT NULL auto_increment,
  `Head` varchar(100) character set utf8 collate utf8_bin NOT NULL,
  `ChkSum` bigint(20) default NULL,
  `Tail` mediumblob,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `jena_XURI` (`Head`,`ChkSum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jena_long_uri
-- ----------------------------

-- ----------------------------
-- Table structure for `jena_prefix`
-- ----------------------------
DROP TABLE IF EXISTS `jena_prefix`;
CREATE TABLE `jena_prefix` (
  `ID` int(11) NOT NULL auto_increment,
  `Head` varchar(100) character set utf8 collate utf8_bin NOT NULL,
  `ChkSum` bigint(20) default NULL,
  `Tail` mediumblob,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `jena_XBND` (`Head`,`ChkSum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jena_prefix
-- ----------------------------

-- ----------------------------
-- Table structure for `jena_sys_stmt`
-- ----------------------------
DROP TABLE IF EXISTS `jena_sys_stmt`;
CREATE TABLE `jena_sys_stmt` (
  `Subj` varchar(100) character set utf8 collate utf8_bin NOT NULL,
  `Prop` varchar(100) character set utf8 collate utf8_bin NOT NULL,
  `Obj` varchar(100) character set utf8 collate utf8_bin NOT NULL,
  `GraphID` int(11) default NULL,
  KEY `jena_XSP` (`Subj`,`Prop`),
  KEY `jena_XO` (`Obj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jena_sys_stmt
-- ----------------------------
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__8000:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#EngineType:', 'Lv:0::MySQL:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__8000:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#DriverVersion:', 'Lv:0::2.0alpha:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__8000:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#LayoutVersion:', 'Lv:0::2.0:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__8000:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#FormatDate:', 'Lv:0::20091205T180712Z:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__8000:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#LongObjectLength:', 'Lv:0::100:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__8000:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#IndexKeyLength:', 'Lv:0::100:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__8000:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#IsTransactionDb:', 'Lv:0::true:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__8000:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#DoCompressURI:', 'Lv:0::false:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__8000:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#CompressURILength:', 'Lv:0::100:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__8000:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#TableNamePrefix:', 'Lv:0::jena_:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7fff:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#GraphName:', 'Lv:0::JENA_DEFAULT_GRAPH_PROPERTIES:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7fff:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#GraphType:', 'Lv:0::generic:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7fff:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#GraphId:', 'Lv:0::0:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffe:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#GraphName:', 'Lv:0::http://localhost:8080/MyOntology :', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffe:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#GraphType:', 'Lv:0::generic:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffe:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#GraphId:', 'Lv:0::1:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffe:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#StmtTable:', 'Lv:0::jena_g1t1_stmt:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffe:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#ReifTable:', 'Lv:0::jena_g1t0_reif:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__8000:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#Graph:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffe:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffd:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#PSetName:', 'Lv:0::192_168_1_102_7232c40e_12560061fd4__7ffc:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffd:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#PSetType:', 'Lv:0::com.hp.hpl.jena.db.impl.PSet_ReifStore_RDB:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffd:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#PSetTable:', 'Lv:0::jena_g1t0_reif:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffb:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#LSetName:', 'Lv:0::LSET_http://localhost:8080/MyOntology _REIFIER:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffb:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#LSetType:', 'Lv:0::com.hp.hpl.jena.db.impl.SpecializedGraphReifier_RDB:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffb:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#LSetPSet:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffd:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffe:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#GraphLSet:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffb:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffa:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#PSetName:', 'Lv:0::192_168_1_102_7232c40e_12560061fd4__7ff9:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffa:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#PSetType:', 'Lv:0::com.hp.hpl.jena.db.impl.PSet_TripleStore_RDB:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffa:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#PSetTable:', 'Lv:0::jena_g1t1_stmt:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ff8:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#LSetName:', 'Lv:0::LSET_http://localhost:8080/MyOntology :', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ff8:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#LSetType:', 'Lv:0::com.hp.hpl.jena.db.impl.SpecializedGraph_TripleStore_RDB:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ff8:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#LSetPSet:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffa:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffe:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#GraphLSet:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ff8:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffe:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#GraphPrefix:', 'Bv::-7232c40e:12560061fd4:-7ff2:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Bv::-7232c40e:12560061fd4:-7ff2:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#PrefixURI:', 'Lv:0::http://www.w3.org/2000/01/rdf-schema#:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Bv::-7232c40e:12560061fd4:-7ff2:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#PrefixValue:', 'Lv:0::rdfs:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffe:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#GraphPrefix:', 'Bv::-7232c40e:12560061fd4:-7ff1:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Bv::-7232c40e:12560061fd4:-7ff1:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#PrefixURI:', 'Lv:0::http://www.w3.org/2002/07/owl#:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Bv::-7232c40e:12560061fd4:-7ff1:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#PrefixValue:', 'Lv:0::owl:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Uv::http://jena.hpl.hp.com/2003/04/DB#192_168_1_102_7232c40e_12560061fd4__7ffe:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#GraphPrefix:', 'Bv::-7232c40e:12560061fd4:-7ff0:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Bv::-7232c40e:12560061fd4:-7ff0:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#PrefixURI:', 'Lv:0::http://www.daml.org/2001/03/daml+oil#:', '0');
INSERT INTO `jena_sys_stmt` VALUES ('Bv::-7232c40e:12560061fd4:-7ff0:', 'Uv::http://jena.hpl.hp.com/2003/04/DB#PrefixValue:', 'Lv:0::daml:', '0');
