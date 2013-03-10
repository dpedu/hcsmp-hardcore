CREATE TABLE IF NOT EXISTS `deaths` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) NOT NULL,
  `Date` datetime NOT NULL,
  `Cause` text NOT NULL,
  `Killer` varchar(50) NOT NULL,
  `Weapon` varchar(50) NOT NULL,
  `Witness` varchar(50) NOT NULL,
  `X` int(11) NOT NULL,
  `Y` int(11) NOT NULL,
  `Z` int(11) NOT NULL,
  `World` varchar(50) NOT NULL,
  `LastWords` text NOT NULL,
  `SurvivalTime` int(11) NOT NULL DEFAULT '0',
  `Revive` enum('None','Admin','Donation') NOT NULL DEFAULT 'None',
  PRIMARY KEY (`ID`),
  KEY `Date` (`Date`),
  FULLTEXT KEY `Username` (`Username`),
  FULLTEXT KEY `Cause` (`Cause`),
  FULLTEXT KEY `Killer` (`Killer`),
  FULLTEXT KEY `Witness` (`Witness`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 ;

CREATE TABLE IF NOT EXISTS `player_status` (
  `Username` varchar(50) NOT NULL,
  `Status` enum('Alive','Dead') NOT NULL,
  `LastUpdated` datetime NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `survival` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) NOT NULL,
  `Joined` datetime NOT NULL,
  `LastOnline` datetime NOT NULL,
  `SurvivalTime` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `Joined` (`Joined`),
  FULLTEXT KEY `Username` (`Username`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;