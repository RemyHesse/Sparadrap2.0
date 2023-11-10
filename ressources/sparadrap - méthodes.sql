/***************************************************************/
/* trigger sur la table utilisateur pour créer un user  	   */
/***************************************************************/

drop trigger if exists apres_ajout_utilisateur;

delimiter µ
create trigger apres_ajout_utilisateur after insert
on UTILISATEUR for each row
begin
		set @requetesql = CONCAT('create user if not exists \'', UTI_LOGIN, '\'@\'localhost\'');
		prepare creation_utilisateur from @requetesql;
		execute creation_utilisateur;
		deallocate prepare creation_utilisateur;
end µ
delimiter ;


/***************************************************************/
/* procédure pour initialiser les droits des utilisateurs 	   */
/***************************************************************/

drop user if exists 'leboss'@'localhost';
create user if not exists 'leboss'@'localhost';
drop user if exists 'Rémy'@'localhost';
create user if not exists 'Rémy'@'localhost';

drop procedure if exists ajout_privilege;

delimiter µ
create procedure ajout_privilege(p_uti_id int, p_dro_id int)
begin
	declare login varchar(30);
    set @id = p_uti_id;
    set @droit = p_dro_id;
        
    select UTI_LOGIN from UTILISATEUR where UTI_ID = p_uti_id into login;
        
    if ( p_dro_id = 3 ) then 
		SET @requetesql = CONCAT('GRANT ALL PRIVILEGES ON sparadrap.* TO \'', login, '\'@\'localhost\' WITH GRANT OPTION');
		PREPARE grant_privilege FROM @requetesql;
		EXECUTE grant_privilege;
		DEALLOCATE PREPARE grant_privilege;
	elseif ( p_dro_id = 2 ) then  -- todo : revoquer les privilèges sur les tables utilisateur / droit
		SET @requetegrant = CONCAT('GRANT SELECT, INSERT, UPDATE, DELETE ON sparadrap.* TO \'', login, '\'@\'localhost\'');
		PREPARE grant_privilege FROM @requetegrant;
		EXECUTE grant_privilege;
        DEALLOCATE PREPARE grant_privilege;
        FLUSH PRIVILEGES;
        
		SET @requeterevoque1 = CONCAT('REVOKE SELECT, INSERT, UPDATE, DELETE ON `sparadrap`.`utilisateur` FROM \'', login, '\'@\'localhost\'');
		SET @requeterevoque2 = CONCAT('REVOKE SELECT, INSERT, UPDATE, DELETE ON `sparadrap`.`droit` FROM \'', login, '\'@\'localhost\'');
        PREPARE revoque_privilege1 FROM @requeterevoque1;
        PREPARE revoque_privilege2 FROM @requeterevoque2;
		EXECUTE revoque_privilege1;
        EXECUTE revoque_privilege2;
                
		DEALLOCATE PREPARE revoque_privilege1;
        DEALLOCATE PREPARE revoque_privilege2;
        
    end if ;
    
end µ
delimiter ;

call ajout_privilege(3, 2);

SHOW GRANTS FOR leboss@localhost; 