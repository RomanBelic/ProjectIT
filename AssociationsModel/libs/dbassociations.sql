-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 22 Janvier 2016 à 00:47
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `dbassociations`
--
CREATE DATABASE IF NOT EXISTS dbassociations;
USE dbassociations;

-- --------------------------------------------------------

--
-- Structure de la table `association`
--

CREATE TABLE IF NOT EXISTS `association` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `LibelleAssociation` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_2` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=120 ;

--
-- Contenu de la table `association`
--

INSERT INTO `association` (`id`, `LibelleAssociation`) VALUES
(1, 'Microsoft'),
(2, 'Apple');

-- --------------------------------------------------------

--
-- Structure de la table `associationdescription`
--

CREATE TABLE IF NOT EXISTS `associationdescription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idAssociation` int(11) NOT NULL,
  `idPresident` int(11) NOT NULL,
  `nomAssoc` varchar(250) DEFAULT NULL,
  `nbParticipant` int(11) DEFAULT NULL,
  `Description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_2` (`id`),
  KEY `idPresident` (`idPresident`),
  KEY `idAssociation` (`idAssociation`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=120 ;

--
-- Contenu de la table `associationdescription`
--

INSERT INTO `associationdescription` (`id`, `idAssociation`, `idPresident`, `nomAssoc`, `nbParticipant`, `Description`) VALUES
(1, 1, 1, 'XBOX', 100, 'Microsoft corp'),
(2, 2, 2, 'MAC', 80, 'Apple corp');

-- --------------------------------------------------------

--
-- Structure de la table `associationevents`
--

CREATE TABLE IF NOT EXISTS `associationevents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateEvent` date NOT NULL,
  `LibelleEvent` varchar(250) NOT NULL,
  `descriptionEvent` varchar(250) NOT NULL,
  `nbParticipant` int(11) NOT NULL,
  `idAssociation` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `idAssociation` (`id`),
  KEY `fk_idAssoc_idx` (`idAssociation`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Contenu de la table `associationevents`
--

INSERT INTO `associationevents` (`id`, `dateEvent`, `LibelleEvent`, `descriptionEvent`, `nbParticipant`, `idAssociation`) VALUES
(1, '2015-12-16', 'Programmation', 'Programmation en C#', 5, 1),
(2, '2015-01-20', 'Programmation', 'Programmation en Swift', 4, 2);

-- --------------------------------------------------------

--
-- Structure de la table `authentification`
--

CREATE TABLE IF NOT EXISTS `authentification` (
  `idUtilisateur` int(11) NOT NULL,
  `Login` varchar(250) NOT NULL,
  `MDP` varchar(250) NOT NULL,
  UNIQUE KEY `Login` (`Login`),
  UNIQUE KEY `idUtilisateur_2` (`idUtilisateur`),
  KEY `fkAuthentification_idx` (`idUtilisateur`),
  KEY `idUtilisateur` (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `authentification`
--

INSERT INTO `authentification` (`idUtilisateur`, `Login`, `MDP`) VALUES
(1, 'log', 'mdp'),
(2, 'login1', 'mdp1'),
(3, 'login2', 'mdp2');

-- --------------------------------------------------------

--
-- Structure de la table `ficheparticipant`
--

CREATE TABLE IF NOT EXISTS `ficheparticipant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idUtilisateur` int(11) NOT NULL,
  `dateInscription` date NOT NULL,
  `dateDesinscription` date DEFAULT NULL,
  `Notes` varchar(250) DEFAULT NULL,
  `Anciennete` int(10) NOT NULL DEFAULT '0',
  `idAssociation` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `idUtilisateur` (`idUtilisateur`),
  KEY `idAssociation` (`idAssociation`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=52 ;

--
-- Contenu de la table `ficheparticipant`
--

INSERT INTO `ficheparticipant` (`id`, `idUtilisateur`, `dateInscription`, `dateDesinscription`, `Notes`, `Anciennete`, `idAssociation`) VALUES
(2, 1, '2015-12-23', NULL, NULL, 0, 2),
(3, 3, '2015-12-30', NULL, NULL, 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `participantsevents`
--

CREATE TABLE IF NOT EXISTS `participantsevents` (
  `idAssociation` int(11) NOT NULL,
  `idEvenement` int(11) NOT NULL,
  `idUtilisateur` int(11) NOT NULL,
  `Presence` int(1) NOT NULL DEFAULT '-1',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `idAssociation` (`idAssociation`),
  KEY `idEvenement` (`idEvenement`),
  KEY `idUtilisateur` (`idUtilisateur`),
  KEY `id_2` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Les nouveaux participants ont statut de presence"-1" !!!' AUTO_INCREMENT=23 ;

--
-- Contenu de la table `participantsevents`
--

INSERT INTO `participantsevents` (`idAssociation`, `idEvenement`, `idUtilisateur`, `Presence`, `id`) VALUES
(1, 1, 2, 1, 1),
(1, 1, 1, 1, 7);

-- --------------------------------------------------------

--
-- Structure de la table `typeutilisateur`
--

CREATE TABLE IF NOT EXISTS `typeutilisateur` (
  `id` int(11) NOT NULL,
  `Libelle` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `typeutilisateur`
--

INSERT INTO `typeutilisateur` (`id`, `Libelle`) VALUES
(1, 'Administrateur Appli'),
(2, 'Administrateur Assoc'),
(3, 'Utilisateur');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE IF NOT EXISTS `utilisateurs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idType` int(11) NOT NULL,
  `nomUtilisateur` varchar(250) DEFAULT NULL,
  `prenomUtilisateur` varchar(255) DEFAULT NULL,
  `adrUtilisateur` varchar(250) DEFAULT NULL,
  `telUtilisateur` varchar(250) DEFAULT NULL,
  `Statut` int(1) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idUtilisateurs_UNIQUE` (`id`),
  KEY `id` (`id`),
  KEY `idType` (`idType`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Les nouveaux utilisateurs ont statut autogenere "-1" !!!' AUTO_INCREMENT=18 ;

--
-- Contenu de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `idType`, `nomUtilisateur`, `prenomUtilisateur`, `adrUtilisateur`, `telUtilisateur`, `Statut`) VALUES
(1, 2, 'Gates', 'Bill', 'Silicon Valley', '1234567890', 1),
(2, 2, 'Jobs', 'Steve', 'Silicon Valley', '0987654321', 1),
(3, 3, 'Allen', 'Paul', 'Seatle', '0235967811', 1),
(4, 3, 'Wozniak', 'Steve', 'San Jose', '9956147723', 1),
(5, 3, 'Verney', 'Louis', '99, rue Marie de Médicis 62400 BÉTHUNE ', '03.68.80.19.73', 1),
(6, 3, 'Boucher', 'Telford', '94, rue Michel Ange\r\n97232 LE LAMENTIN ', '01.51.93.43.38', 1),
(7, 3, 'Despins', 'Aloin', '24, boulevard Amiral Courbet\r\n01100 OYONNAX ', '04.56.79.96.46', 1),
(8, 3, 'Dupuis', 'Carolos', '48, Avenue des Tuileries\r\n78280 GUYANCOURT ', '01.26.72.46.57', 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `associationdescription`
--
ALTER TABLE `associationdescription`
  ADD CONSTRAINT `fk_idAssociation` FOREIGN KEY (`idAssociation`) REFERENCES `association` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idPres` FOREIGN KEY (`idPresident`) REFERENCES `utilisateurs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `associationevents`
--
ALTER TABLE `associationevents`
  ADD CONSTRAINT `fk_idAssoc` FOREIGN KEY (`idAssociation`) REFERENCES `association` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `authentification`
--
ALTER TABLE `authentification`
  ADD CONSTRAINT `fk_idUser` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateurs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `ficheparticipant`
--
ALTER TABLE `ficheparticipant`
  ADD CONSTRAINT `fk_idAssoc2` FOREIGN KEY (`idAssociation`) REFERENCES `association` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idUser2` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateurs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `participantsevents`
--
ALTER TABLE `participantsevents`
  ADD CONSTRAINT `fk_idAssoc3` FOREIGN KEY (`idAssociation`) REFERENCES `association` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idEvent` FOREIGN KEY (`idEvenement`) REFERENCES `associationevents` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idUser3` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateurs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD CONSTRAINT `fk_typeuser` FOREIGN KEY (`idType`) REFERENCES `typeutilisateur` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
