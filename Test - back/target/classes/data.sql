

INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

              
INSERT INTO `test`.`prevoznik` (`id`, `adresa`, `naziv`, `pib`) VALUES ('1', 'adresa1', 'Kolasin', '123a');
INSERT INTO `test`.`prevoznik` (`id`, `adresa`, `naziv`, `pib`) VALUES ('2', 'adresa2', 'Nis Express', '123b');
INSERT INTO `test`.`prevoznik` (`id`, `adresa`, `naziv`, `pib`) VALUES ('3', 'adresa3', 'Transport AC', '123c');

INSERT INTO `test`.`linija` (`id`, `broj_mesta`, `cena_karte`, `destinacija`, `vreme_polaska`, `prevoznik_id`) VALUES ('1', '24', '1200', 'Novi Sad', '11:00', '1');
INSERT INTO `test`.`linija` (`id`, `broj_mesta`, `cena_karte`, `destinacija`, `vreme_polaska`, `prevoznik_id`) VALUES ('2', '58', '800', 'Beograd', '11:00', '2');
INSERT INTO `test`.`linija` (`id`, `broj_mesta`, `cena_karte`, `destinacija`, `vreme_polaska`, `prevoznik_id`) VALUES ('3', '100', '1500', 'Kraljevo', '12:00', '3');
INSERT INTO `test`.`linija` (`id`, `broj_mesta`, `cena_karte`, `destinacija`, `vreme_polaska`, `prevoznik_id`) VALUES ('4', '36', '1000', 'Kragujevac', '09:00', '1');
INSERT INTO `test`.`linija` (`id`, `broj_mesta`, `cena_karte`, `destinacija`, `vreme_polaska`, `prevoznik_id`) VALUES ('5', '44', '850', 'Nis', '15:00', '3');
