-- MySQL Script generated by MySQL Workbench
-- Tue Sep 14 08:27:23 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fundalink
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fundalink
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fundalink` DEFAULT CHARACTER SET utf8 ;
USE `fundalink` ;

-- -----------------------------------------------------
-- Table `fundalink`.`Fundaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fundalink`.`Fundaciones` (
  `idFundacion` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NULL,
  `Direccion` VARCHAR(100) NULL,
  `Email` VARCHAR(100) NULL,
  `Representante` VARCHAR(100) NULL,
  `URL` VARCHAR(100) NULL,
  `Telefono` VARCHAR(45) NULL,
  `Username` VARCHAR(45) NULL,
  `Password` VARCHAR(45) NULL,
  `Fotouno` LONGBLOB NULL,
  `Fotodos` LONGBLOB NULL,
  `Fototres` LONGBLOB NULL,
  `Descripcion` VARCHAR(500) NULL,
  `Tipo` VARCHAR(45) NULL,
  PRIMARY KEY (`idFundacion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fundalink`.`Proyectos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fundalink`.`Proyectos` (
  `idProyectos` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(1000) NULL,
  `Fundaciones_idFundacion` INT NOT NULL,
  PRIMARY KEY (`idProyectos`, `Fundaciones_idFundacion`),
  INDEX `fk_Proyectos_Fundaciones1_idx` (`Fundaciones_idFundacion` ASC) VISIBLE,
  UNIQUE INDEX `idProyectos_UNIQUE` (`idProyectos` ASC) VISIBLE,
  CONSTRAINT `fk_Proyectos_Fundaciones1`
    FOREIGN KEY (`Fundaciones_idFundacion`)
    REFERENCES `fundalink`.`Fundaciones` (`idFundacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fundalink`.`Aliados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fundalink`.`Aliados` (
  `idAlidos` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `Telefono` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  `Alianzas_idAlianzas` INT NOT NULL,
  `Alianzas_Fundaciones_idFundacion` INT NOT NULL,
  `Username` VARCHAR(45) NULL,
  `Password` VARCHAR(45) NULL,
  PRIMARY KEY (`idAlidos`, `Alianzas_Fundaciones_idFundacion`, `Alianzas_idAlianzas`),
  UNIQUE INDEX `idAlidos_UNIQUE` (`idAlidos` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fundalink`.`Alianzas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fundalink`.`Alianzas` (
  `idAlianzas` INT NOT NULL AUTO_INCREMENT,
  `Fundaciones_idFundacion` INT NOT NULL,
  `Aliados_idAlidos` INT NOT NULL,
  `Aliados_Alianzas_Fundaciones_idFundacion` INT NOT NULL,
  `Aliados_Alianzas_idAlianzas` INT NOT NULL,
  PRIMARY KEY (`idAlianzas`, `Fundaciones_idFundacion`, `Aliados_idAlidos`, `Aliados_Alianzas_Fundaciones_idFundacion`, `Aliados_Alianzas_idAlianzas`),
  UNIQUE INDEX `idAlianzas_UNIQUE` (`idAlianzas` ASC) VISIBLE,
  INDEX `fk_Alianzas_Fundaciones1_idx` (`Fundaciones_idFundacion` ASC) VISIBLE,
  INDEX `fk_Alianzas_Aliados1_idx` (`Aliados_idAlidos` ASC, `Aliados_Alianzas_Fundaciones_idFundacion` ASC, `Aliados_Alianzas_idAlianzas` ASC) VISIBLE,
  CONSTRAINT `fk_Alianzas_Fundaciones1`
    FOREIGN KEY (`Fundaciones_idFundacion`)
    REFERENCES `fundalink`.`Fundaciones` (`idFundacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alianzas_Aliados1`
    FOREIGN KEY (`Aliados_idAlidos` , `Aliados_Alianzas_Fundaciones_idFundacion` , `Aliados_Alianzas_idAlianzas`)
    REFERENCES `fundalink`.`Aliados` (`idAlidos` , `Alianzas_Fundaciones_idFundacion` , `Alianzas_idAlianzas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fundalink`.`PQR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fundalink`.`PQR` (
  `idPQR` INT NOT NULL AUTO_INCREMENT,
  `Pregunta` VARCHAR(1000) NULL,
  `Respuesta` VARCHAR(1000) NULL,
  `Proyectos_idProyectos` INT NOT NULL,
  PRIMARY KEY (`idPQR`, `Proyectos_idProyectos`),
  INDEX `fk_PQR_Proyectos1_idx` (`Proyectos_idProyectos` ASC) VISIBLE,
  UNIQUE INDEX `idPQR_UNIQUE` (`idPQR` ASC) VISIBLE,
  CONSTRAINT `fk_PQR_Proyectos1`
    FOREIGN KEY (`Proyectos_idProyectos`)
    REFERENCES `fundalink`.`Proyectos` (`idProyectos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fundalink`.`Aliados_has_PQR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fundalink`.`Aliados_has_PQR` (
  `Aliados_idAlidos` INT NOT NULL,
  `Aliados_Alianzas_Fundaciones_idFundacion` INT NOT NULL,
  `Aliados_Alianzas_idAlianzas` INT NOT NULL,
  `PQR_idPQR` INT NOT NULL,
  `PQR_Proyectos_idProyectos` INT NOT NULL,
  PRIMARY KEY (`Aliados_idAlidos`, `Aliados_Alianzas_Fundaciones_idFundacion`, `Aliados_Alianzas_idAlianzas`, `PQR_idPQR`, `PQR_Proyectos_idProyectos`),
  INDEX `fk_Aliados_has_PQR_PQR1_idx` (`PQR_idPQR` ASC, `PQR_Proyectos_idProyectos` ASC) VISIBLE,
  INDEX `fk_Aliados_has_PQR_Aliados1_idx` (`Aliados_idAlidos` ASC, `Aliados_Alianzas_Fundaciones_idFundacion` ASC, `Aliados_Alianzas_idAlianzas` ASC) VISIBLE,
  CONSTRAINT `fk_Aliados_has_PQR_Aliados1`
    FOREIGN KEY (`Aliados_idAlidos` , `Aliados_Alianzas_Fundaciones_idFundacion` , `Aliados_Alianzas_idAlianzas`)
    REFERENCES `fundalink`.`Aliados` (`idAlidos` , `Alianzas_Fundaciones_idFundacion` , `Alianzas_idAlianzas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aliados_has_PQR_PQR1`
    FOREIGN KEY (`PQR_idPQR` , `PQR_Proyectos_idProyectos`)
    REFERENCES `fundalink`.`PQR` (`idPQR` , `Proyectos_idProyectos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;