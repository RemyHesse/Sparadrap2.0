drop database if exists sparadrap;
create database sparadrap;
use sparadrap;

drop table if exists CATEGORIE;
drop table if exists MEDICAMENT;
drop table if exists DEPARTEMENT;
drop table if exists VILLE;
drop table if exists ADRESSE;
drop table if exists MUTUELLE;
drop table if exists PERSONNE;
drop table if exists CLIENT;
drop table if exists MEDECIN;
drop table if exists SPECIALITE;
drop table if exists ACHAT;
drop table if exists VENTE;
drop table if exists ORDONNANCE;
drop table if exists DELIVRE;
drop table if exists PRESCRIPTION;
drop table if exists PRESCRIPTION;

/*==============================================================*/
/* Table : CATEGORIE                                            */
/*==============================================================*/
create table if not exists CATEGORIE(
	CAT_ID tinyint auto_increment not null,
    CAT_LABEL varchar(30),
    primary key (CAT_ID)
);

/*==============================================================*/
/* Table : MEDICAMENT                                           */
/*==============================================================*/
create table if not exists MEDICAMENT(
	MED_ID int auto_increment not null,
	CAT_ID int not null,
    MED_NOM varchar(30),
    MED_PRIX float,
    MED_MISE_EN_SERVICE date,
    primary key (MED_ID),
    constraint FK_MED_CAT foreign key (CAT_ID) references CATEGORIE(CAT_ID)
);

/*==============================================================*/
/* Table : DEPARTEMENT                                          */
/*==============================================================*/
create table if not exists DEPARTEMENT(
	DEP_ID int auto_increment not null,
    DEP_NUMERO int not null,
    DEP_NOM varchar(30),
    primary key (DEP_ID)
);

/*==============================================================*/
/* Table : VILLE                   		                        */
/*==============================================================*/
create table if not exists VILLE(
	VIL_ID int auto_increment not null,
    DEP_ID int not null,
    VIL_CP int(5) not null,
    VIL_NOM varchar(45),
    primary key (VIL_ID),
    constraint FK_VIL_DEP foreign key (DEP_ID) references DEPARTEMENT(DEP_ID)
);

/*==============================================================*/
/* Table : ADRESSE                                              */
/*==============================================================*/
create table if not exists ADRESSE(
	ADR_ID int auto_increment not null,
    VIL_ID int not null,
    ADR_NUM int(5),
    ADR_LIGNE1 varchar(50),
    ADR_COMPLEMENT varchar(50),
    primary key (ADR_ID),
    constraint FK_ADR_VIL foreign key (VIL_ID) references VILLE(VIL_ID)
);

/*==============================================================*/
/* Table : MUTUELLE                                             */
/*==============================================================*/
create table if not exists MUTUELLE(
	MUT_ID int auto_increment not null,
    ADR_ID int not null,
    MUT_NOM varchar(25),
    MUT_EMAIL varchar(50),
    MUT_TAUX int(3),
    primary key (MUT_ID),
    constraint FK_MUT_ADR foreign key (ADR_ID) references ADRESSE(ADR_ID)
);

/*==============================================================*/
/* Table : PERSONNE                                             */
/*==============================================================*/
create table if not exists PERSONNE(
	PER_ID int auto_increment not null,
    ADR_ID int not null,
    PER_NOM varchar(30),
    PER_PRENOM varchar(30),
    PER_EMAIL varchar(50),
    PER_TEL int(3),
    primary key (PER_ID),
    constraint FK_PER_ADR foreign key (ADR_ID) references ADRESSE(ADR_ID)
);

/*==============================================================*/
/* Table : CLIENT                                               */
/*==============================================================*/
create table if not exists CLIENT(
	CLI_ID int auto_increment not null,
    MUT_ID int not null,
    CLI_DATE_NAISS date,
    CLI_NUM_SECU bigint,
    primary key (CLI_ID),
    constraint FK_CLI_PER foreign key (CLI_ID) references PERSONNE(PER_ID),
    constraint FK_CLI_MUT foreign key (MUT_ID) references MUTUELLE(MUT_ID)
);

/*==============================================================*/
/* Table : MEDECIN                                              */
/*==============================================================*/
create table if not exists MEDECIN(
	MED_ID int auto_increment not null,
    MED_AGREMENT int not null,
    primary key (MED_ID),
    constraint FK_MED_PER foreign key (MED_ID) references PERSONNE(PER_ID)  
);

/*==============================================================*/
/* Table : SPECIALITE                                           */
/*==============================================================*/
create table if not exists SPECIALITE(
	SPE_ID int auto_increment not null,
    SPE_LABEL varchar(30) not null,
    primary key (SPE_ID)
);

/*==============================================================*/
/* Table : SPECIALISTE                                          */
/*==============================================================*/
create table if not exists SPECIALISTE(
	MED_ID int not null,
    SPE_ID int not null,
    primary key (MED_ID, SPE_ID),
    constraint FK_SPEC_SPE foreign key (SPE_ID) references SPECIALITE(SPE_ID),
    constraint FK_SPEC_MED foreign key (MED_ID) references MEDECIN(MED_ID)
);

/*==============================================================*/
/* Table : ACHAT                                                */
/*==============================================================*/
create table if not exists ACHAT(
	ACH_ID int auto_increment not null,
    ACH_DATE date,
    ACH_PRIX_TOTAL float,
    primary key (ACH_ID)
);

/*==============================================================*/
/* Table : VENTE                                                */
/*==============================================================*/
create table if not exists VENTE(
	ACH_ID int not null,
    MED_ID int not null,
    MED_QTTE int,
    primary key (ACH_ID, MED_ID),
    constraint FK_VEN_ACH foreign key (ACH_ID) references ACHAT(ACH_ID),
    constraint FK_VEN_MED foreign key (MED_ID) references MEDICAMENT(MED_ID)
);

/*==============================================================*/
/* Table : ORDONNANCE                                           */
/*==============================================================*/
create table if not exists ORDONNANCE(
	ORD_ID int auto_increment not null,
    ORD_DATE date,
    primary key (ORD_ID)
);

/*==============================================================*/
/* Table : DELIVRE                                              */
/*==============================================================*/
create table if not exists DELIVRE(
	MED_ID int not null,
    CLI_ID int not null,
    ORD_ID int not null,
    primary key (MED_ID, CLI_ID, ORD_ID),
    constraint FK_DEL_MED foreign key (MED_ID) references MEDECIN(MED_ID),
    constraint FK_DEL_CLI foreign key (CLI_ID) references CLIENT(CLI_ID),
    constraint FK_DEL_ORD foreign key (ORD_ID) references ORDONNANCE(ORD_ID)
);

/*==============================================================*/
/* Table : PRESCRIPTION                                                */
/*==============================================================*/
create table if not exists PRESCRIPTION(
	ORD_ID int not null,
    MED_ID int not null,
    MED_QTTE int,
    primary key (ORD_ID, MED_ID),
    constraint FK_PRE_ORD foreign key (ORD_ID) references ORDONNANCE(ORD_ID),
    constraint FK_PRE_MED foreign key (MED_ID) references MEDICAMENT(MED_ID)
);

/*==============================================================*/
/* Table : VENTE_ORDO                                           */
/*==============================================================*/
create table if not exists VENTE_ORDO(
	ORD_ID int not null,
    ACH_ID int not null,
    primary key (ORD_ID, ACH_ID),
    constraint FK_VENO_ORD foreign key (ORD_ID) references ORDONNANCE(ORD_ID),
    constraint FK_VENO_ACH foreign key (ACH_ID) references ACHAT(ACH_ID)
);

lock tables CATEGORIE write;
insert into CATEGORIE ( CAT_LABEL ) values ("AINS"), ("Antiviral"), ("Antitussif");
unlock tables;

lock tables MEDICAMENT write;
INSERT INTO MEDICAMENT (CAT_ID, MED_NOM, MED_PRIX, MED_MISE_EN_SERVICE) VALUES 
(1, 'Advil', 5.0, '1985-05-15'), (1, 'Nurofen', 5.5, '2002-04-10'), (1, 'Nifluril', 3.5, '1996-07-15'),
(2, 'Antiviral', 7.0, '1996-07-15'), (2, 'Valaciclovir', 6.0, '2009-01-12'), (2, 'Zovirax', 4.5, '1993-12-24'),
(3, 'Dextrométhorphane', 6.5, '2018-03-13'), (3, 'Pholcodine', 8.0, '1993-12-24'), (3, 'Codéine', 4.5, '1996-08-28');
unlock tables;

LOCK TABLES DEPARTEMENT WRITE;
INSERT INTO DEPARTEMENT (DEP_NUMERO, DEP_NOM) VALUES
(1, 'Ain'), (2, 'Aisne'), (3, 'Allier'), (4, 'Alpes-de-Haute-Provence'), (5, 'Hautes-Alpes'),
(6, 'Alpes-Maritimes'), (7, 'Ardèche'), (8, 'Ardennes'), (9, 'Ariège'), (10, 'Aube');
UNLOCK TABLES;

LOCK TABLES VILLE WRITE;
INSERT INTO VILLE (DEP_ID, VIL_CP, VIL_NOM) VALUES
(1, 01000, 'Bourg-en-Bresse'), (1, 01100, 'Oyonnax'), (2, 02000, 'Laon'), (2, 02100, 'Saint-Quentin'), (3, 03000, 'Moulins'),
(3, 03100, 'Montluçon'), (4, 04000, 'Digne-les-Bains'), (4, 04100, 'Manosque'), (5, 05000, 'Gap'), (5, 05100, 'Briançon'),
(6, 06000, 'Nice'), (6, 06100, 'Cannes'), (7, 07000, 'Privas'), (7, 07100, 'Annonay'), (8, 08000, 'Charleville-Mézières'),
(8, 08100, 'Rethel'), (9, 09000, 'Foix'), (9, 09100, 'Pamiers'), (10, 10000, 'Troyes'), (10, 10100, 'Romilly-sur-Seine');
UNLOCK TABLES;

LOCK TABLES ADRESSE WRITE;
INSERT INTO ADRESSE (VIL_ID, ADR_NUM, ADR_LIGNE1, ADR_COMPLEMENT) VALUES
(1, 1, '12 Rue de la République', 'Appartement 5'), (2, 45, '8 Avenue Pasteur', NULL), (3, 32, '25 Rue Victor Hugo', NULL),
(4, 7, '14 Boulevard Jean Jaurès', 'Résidence les Fleurs'), (5, 56, '1 Rue du Commerce', NULL),
(6, 100, '18 Promenade des Anglais', 'Étage 3'), (7, 15, '9 Place du Marché', NULL), (8, 23, '27 Rue de la Paix', NULL),
(9, 4, '3 Avenue des Pyrénées', NULL), (10, 80, '42 Rue de la Liberté', 'Appartement 2'), (11, 5, '37 Rue de la Liberté', 'Appartement 2B'),
(12, 29, '14 Rue de la Paix', NULL), (13, 123, '1 Place de la République', NULL), (14, 12, '3 Rue du Commerce', NULL),
(15, 18, '20 Avenue Pasteur', NULL), (16, 2, '5 Boulevard Jean Jaurès', 'Résidence Bellevue'), (17, 6, '8 Promenade des Anglais', 'Étage 1'),
(18, 19, '42 Rue Victor Hugo', NULL), (19, 14, '30 Avenue des Pyrénées', NULL), (20, 57, '11 Place du Marché', NULL);
UNLOCK TABLES;

LOCK TABLES MUTUELLE WRITE;
INSERT INTO MUTUELLE (ADR_ID, MUT_NOM, MUT_EMAIL, MUT_TAUX) VALUES
(11, 'APREVA', 'contact@APREVA.com', 20),
(12, 'MUTAMI', 'contact@MUTAMI.com', 15),
(13, 'MGEN', 'contact@MGEN.com', 18),
(14, 'Myriade', 'contact@mMyriade.com', 25),
(15, 'SORUAL', 'contact@SORUAL.com', 35);
UNLOCK TABLES;

LOCK TABLES PERSONNE WRITE;
INSERT INTO PERSONNE (ADR_ID, PER_NOM, PER_PRENOM, PER_EMAIL, PER_TEL) VALUES
(1, 'Martin', 'Jean', 'jean.martin@email.com', 0123456789), (2, 'Durand', 'Sophie', 'sophie.durand@email.com', 0987654321),
(3, 'Dubois', 'Pierre', 'pierre.dubois@email.com', 0555555555), (4, 'Lefebvre', 'Marie', 'marie.lefebvre@email.com', 0666666666),
(5, 'Moreau', 'Thomas', 'thomas.moreau@email.com', 0777777777), (6, 'Leroy', 'Isabelle', 'isabelle.leroy@email.com', 0888888888),
(7, 'Roux', 'Michel', 'michel.roux@email.com', 0111111111), (8, 'Girard', 'Catherine', 'catherine.girard@email.com', 0222222222),
(9, 'Garnier', 'David', 'david.garnier@email.com', 0333333333), (10, 'Fontaine', 'Christine', 'christine.fontaine@email.com', 0444444444),
(11, 'Roche', 'Paul', 'paul.roche@email.com', 0999999999), (12, 'Brun', 'Anne', 'anne.brun@email.com', 0888899999),
(13, 'Marchand', 'François', 'francois.marchand@email.com', 0777788888), (14, 'Noël', 'Valérie', 'valerie.noel@email.com', 0666699999),
(15, 'Andre', 'Marc', 'marc.andre@email.com', 0555566666), (16, 'Lemaire', 'Nathalie', 'nathalie.lemaire@email.com', 0444477777),
(17, 'Rivière', 'Nicolas', 'nicolas.riviere@email.com', 0333388888), (18, 'Faure', 'Caroline', 'caroline.faure@email.com', 0222299999),
(19, 'Guillaume', 'Olivier', 'olivier.guillaume@email.com', 0111100000), (20, 'Leclerc', 'Camille', 'camille.leclerc@email.com', 0000011111);
UNLOCK TABLES;

LOCK TABLES CLIENT WRITE;
INSERT INTO CLIENT (CLI_ID, MUT_ID, CLI_DATE_NAISS, CLI_NUM_SECU) VALUES
(1, 1, '1990-05-15', 1900567890123), (2, 2, '1985-07-10', 9876543210123), (3, 3, '1982-03-25', 4567890123123),
(4, 4, '1988-12-04', 7890123123456), (5, 5, '1995-02-18', 3456789012345), (6, 1, '1997-11-29', 5678901231234),
(7, 2, '1986-09-21', 6543210123456), (8, 3, '1978-06-14', 2345678901234), (9, 4, '1989-04-05', 8901231234567),
(10, 5, '1993-08-02', 4321012345678);
UNLOCK TABLES;

LOCK TABLES SPECIALITE WRITE;
INSERT INTO SPECIALITE (SPE_LABEL) VALUES
("Généraliste"),
("Virologue"),
("Pneumologue");
UNLOCK TABLES;

LOCK TABLES MEDECIN WRITE;
INSERT INTO MEDECIN (MED_ID, MED_AGREMENT) VALUES
(11, 12345), (12, 54321), (13, 67890), (14, 98765), (15, 56789);

LOCK TABLES SPECIALISTE WRITE;
INSERT INTO SPECIALISTE (MED_ID, SPE_ID) VALUES
(11, 1), (12, 1), (13, 1), -- Généraliste
(14, 2), -- Virologue
(15, 3); -- Pneumologue
UNLOCK TABLES;

LOCK TABLES ORDONNANCE WRITE;
INSERT INTO ORDONNANCE (ORD_DATE) VALUES
("2023-10-30"), ("2023-10-20"), ("2023-10-10"), ("2023-10-01"),
("2023-09-30"), ("2023-09-20"), ("2023-09-10"), ("2023-09-01"),
("2023-08-30"), ("2023-09-20"), ("2023-09-10"), ("2023-09-01");
UNLOCK TABLES;

LOCK TABLES DELIVRE WRITE;
INSERT INTO DELIVRE (MED_ID, CLI_ID, ORD_ID) VALUES
(11, 1, 1), (11, 1, 2), (12, 2, 3), (12, 4, 4),
(13, 5, 5), (13, 6, 6), (14, 7, 7), (14, 7, 8),
(15, 8, 9), (15, 9, 10), (11, 1, 11), (12, 3, 12);
UNLOCK TABLES;

LOCK TABLES PRESCRIPTION WRITE;
INSERT INTO PRESCRIPTION (ORD_ID, MED_ID, MED_QTTE) VALUES
(1, 1, 3), (1, 2, 2), (2, 2, 3), (2, 3, 4), (3, 1, 2), (3, 2, 4), (4, 2, 3), (4, 3, 3),
(5, 1, 5), (5, 3, 6), (6, 2, 7), (6, 3, 8), (7, 4, 3), (7, 5, 5), (8, 5, 2), (8, 6, 5),
(9, 7, 2), (9, 8, 4), (10, 8, 5), (10, 9, 5), (11, 2, 5), (11, 3, 3), (12, 1, 4), (12, 2, 3);
UNLOCK TABLES;

LOCK TABLES ACHAT WRITE;
INSERT INTO ACHAT (ACH_DATE, ACH_PRIX_TOTAL) VALUES
("2023-10-31", '26'), ("2023-10-21", '30.5'), ("2023-10-11", '22'), ("2023-10-02" , '27'),
("2023-10-01", '46'), ("2023-09-21", '66.5'), ("2023-09-11", '51'), ("2023-09-02", '34.5'),
("2023-08-31", '37'), ("2023-09-21", '62.5'), ("2023-09-11", '38'), ("2023-09-02", '36.5'),
("2023-08-31", '21'), ("2023-09-21", '18'), ("2023-09-11", '34'), ("2023-09-02", '45');
UNLOCK TABLES;

LOCK TABLES VENTE_ORDO WRITE;
INSERT INTO VENTE_ORDO (ORD_ID, ACH_ID) VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8),
(9, 9), (10, 10), (11, 11), (12, 12);
UNLOCK TABLES;

LOCK TABLES VENTE WRITE;
INSERT INTO VENTE (ACH_ID, MED_ID, MED_QTTE) VALUES
(13, 1, 2), (13, 2, 2), (14, 2, 2), (14, 3, 2), (15, 1, 4), (15, 3, 4), (16, 2, 5), (16, 3 , 5);
UNLOCK TABLES;















