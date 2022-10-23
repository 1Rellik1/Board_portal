INSERT INTO chance_card (id, description, money_difference, step, type)
VALUES (1, 'решает отправиться в незабываемое путешествие. Он проходит на 6 шагов вперед ', null, 6, 'CHANGE_POSITION'),
       (2, 'богатый родственник оставил вам наследство, получаете 500К', 500, null, 'CHANGE_BALANCE'),
       (3, 'празднует сегодня День рождения! Банк дарит ему 1000K ', 1000, null, 'CHANGE_BALANCE'),
       (4, 'получает банковские дивиденты в размере 700К ', 700, null, 'CHANGE_BALANCE'),
       (5, 'выигрывает чемпионат по монополии и получает 100К ', 100, null, 'CHANGE_BALANCE'),
       (6, ',банковская ошибка в вашу пользу. Получите 200К ', 200, null, 'CHANGE_BALANCE'),
       (7, 'богатый родственник оставил вам наследство, получаете 1000К ', 1000, null, 'CHANGE_BALANCE'),
       (8, 'богатый родственник оставил вам наследство, получаете 700К ', 700, null, 'CHANGE_BALANCE'),
       (9, 'поучает страховые выплаты в размере 800K ', 800, null, 'CHANGE_BALANCE'),
       (10, 'Куда летишь! Вернись на 5 шагов назад', null, -5, 'CHANGE_POSITION'),
       (11, 'не пей за рулем: пройди на 10 шагов назад ', null, -10, 'CHANGE_POSITION'),
       (12, 'не твой день: Пройдите на 15 шагов назад ', null, -15, 'CHANGE_POSITION'),
       (13, 'неожиданно возвращает старый долг 800К ', 800, null, 'CHANGE_BALANCE'),
       (14, 'выигрывает в лотерею 300К ', 300, null, 'CHANGE_BALANCE'),
       (15, 'Ой, а зубы то у вас плохие, заплатите за лечение 1000К ', -1000, null, 'CHANGE_POSITION');

INSERT INTO common_card (id, image, card_type)
VALUES (1, '/images/monopoly_board_start.png', 'START'),
       (2, '/images/kaliningrad.svg', 'COMPANY'),
       (3, '/images/chance-1.png', 'CHANCE'),
       (4, '/images/sovetsk.png', 'COMPANY'),
       (5, '/images/tax_income.png', 'TAX_INCOME'),
       (6, '/images/moscow.svg', 'COMPANY'),
       (7, '/images/ufa.svg', 'COMPANY'),
       (8, '/images/chance-1.png', 'CHANCE'),
       (9, '/images/sterlitamak.png', 'COMPANY'),
       (10, '/images/salavat.png', 'COMPANY'),
       (11, '/images/jail.png', 'NONTYPE'),
       (12, '/images/irkutsk.svg', 'COMPANY'),
       (13, '/images/vladivostok.svg', 'COMPANY'),
       (14, '/images/bratsk.png', 'COMPANY'),
       (15, '/images/angarsk.svg', 'COMPANY'),
       (16, '/images/novosibirsk.svg', 'COMPANY'),
       (17, '/images/krasnoyarsk.svg', 'COMPANY'),
       (18, '/images/chance-3.png', 'CHANCE'),
       (19, '/images/norilsk.png', 'COMPANY'),
       (20, '/images/achinsk.png', 'COMPANY'),
       (21, '/images/jackpot.svg', 'JACKPOT'),
       (22, '/images/rostov_on_don.svg', 'COMPANY'),
       (23, '/images/chance-4.png', 'CHANCE'),
       (24, '/images/taganrog.png', 'COMPANY'),
       (25, '/images/shahti.png', 'COMPANY'),
       (26, '/images/saint_petersburg.svg', 'COMPANY'),
       (27, '/images/kazan.svg', 'COMPANY'),
       (28, '/images/naberezhnye_chelny.svg', 'COMPANY'),
       (29, '/images/murmansk.svg', 'COMPANY'),
       (30, '/images/nizhnekamsk.png', 'COMPANY'),
       (31, '/images/goToJail.png', 'TELEPORT'),
       (32, '/images/krasnodar.png', 'COMPANY'),
       (33, '/images/sochi.svg', 'COMPANY'),
       (34, '/images/chance-5.png', 'CHANCE'),
       (35, '/images/novorossiysk.png', 'COMPANY'),
       (36, '/images/yekaterinburg.svg', 'COMPANY'),
       (37, '/images/tax_luxury.png', 'TAX_LUXURY'),
       (38, '/images/sevastopol.svg', 'COMPANY'),
       (39, '/images/chance-5.png', 'CHANCE'),
       (40, '/images/simferopol.svg', 'COMPANY');

INSERT INTO company_card (id, title, region, price, star_price, sale_price, collection_number, common_card_id)
VALUES (2, 'Калининград', 'Калининградская область', 600, 500, 300, 1, 2),
       (4, 'Советск', 'Калининградская область', 800, 500, 400, 1, 4),
       (6, 'Москва', 'Города миллионники', 2000, 1200, 1000, 2, 6),
       (7, 'Уфа', 'Республика Башкортостан', 1000, 500, 500, 3, 7),
       (9, 'Стерлитамак', 'Республика Башкортостан', 1100, 500, 550, 3, 9),
       (10, 'Салават', 'Республика Башкортостан', 1200, 500, 600, 3, 10),
       (12, 'Иркутск', 'Иркутская область', 1400, 750, 700, 4, 12),
       (13, 'Владивосток', 'Портовые города', 1500, 800, 750, 5, 13),
       (14, 'Братск', 'Иркутская область', 1500, 750, 750, 4, 14),
       (15, 'Ангарск', 'Иркутская область', 1600, 750, 800, 4, 15),
       (16, 'Новосибирск', 'Города миллионники', 2000, 1200, 1000, 2, 16),
       (17, 'Красноярск', 'Красноярский край', 1800, 1000, 900, 6, 17),
       (19, 'Норильск', 'Красноярский край', 1900, 1000, 950, 6, 19),
       (20, 'Ачинск', 'Красноярский край', 2000, 1000, 1000, 6, 20),
       (22, 'Ростов-на-Дону', 'Ростовская область', 2200, 1250, 1100, 7, 22),
       (24, 'Таганрог', 'Ростовская область', 2300, 1250, 1150, 7, 24),
       (25, 'Шахты', 'Ростовская область', 2400, 1250, 1200, 7, 25),
       (26, 'Санкт-Петербург', 'Города миллионники', 2000, 1200, 1000, 2, 26),
       (27, 'Казань', 'Республика Татарстан', 2600, 1500, 1300, 8, 27),
       (28, 'Набережные челны', 'Республика Татарстан', 2700, 1500, 1350, 8, 28),
       (29, 'Мурманск', 'Портовые города', 1500, 800, 900, 5, 29),
       (30, 'Нижнекамск', 'Республика Татарстан', 2800, 1500, 1400, 8, 30),
       (32, 'Краснодар', 'Краснодарская область', 3000, 1750, 1500, 9, 32),
       (33, 'Сочи', 'Краснодарская область', 3100, 1750, 1550, 9, 33),
       (35, 'Новороссийск', 'Краснодарская область', 3200, 1750, 1600, 9, 35),
       (36, 'Екатеринбург', 'Города миллионники', 2000, 1200, 1000, 2, 36),
       (38, 'Севастополь', 'Республика Крым', 3500, 2000, 1750, 10, 38),
       (40, 'Симферополь', 'Республика Крым', 4000, 2000, 2000, 10, 40);

INSERT INTO level_fine (id, value)
VALUES (1, 20),
       (2, 100),
       (3, 300),
       (4, 900),
       (5, 1600),
       (6, 2500);
INSERT INTO level_fine (id, value)
VALUES (7, 40),
       (8, 200),
       (9, 600),
       (10, 1800),
       (11, 3200),
       (12, 4500);
INSERT INTO level_fine (id, value)
VALUES (13, 300),
       (14, 900),
       (15, 2000),
       (16, 4500),
       (17, 9200),
       (18, 12000);
INSERT INTO level_fine (id, value)
VALUES (19, 60),
       (20, 300),
       (21, 900),
       (22, 2700),
       (23, 4000),
       (24, 5500);
INSERT INTO level_fine (id, value)
VALUES (25, 60),
       (26, 300),
       (27, 900),
       (28, 2700),
       (29, 4000),
       (30, 5500);
INSERT INTO level_fine (id, value)
VALUES (31, 80),
       (32, 400),
       (33, 1000),
       (34, 3000),
       (35, 4500),
       (36, 6000);
INSERT INTO level_fine (id, value)
VALUES (37, 100),
       (38, 500),
       (39, 1500),
       (40, 4500),
       (41, 6250),
       (42, 7500);
INSERT INTO level_fine (id, value)
VALUES (43, 150),
       (44, 400),
       (45, 900),
       (46, 1600),
       (47, 3700),
       (48, 6200);
INSERT INTO level_fine (id, value)
VALUES (49, 100),
       (50, 500),
       (51, 1500),
       (52, 4500),
       (53, 6250),
       (54, 7500);
INSERT INTO level_fine (id, value)
VALUES (55, 120),
       (56, 600),
       (57, 1800),
       (58, 5000),
       (59, 7000),
       (60, 9000);
INSERT INTO level_fine (id, value)
VALUES (61, 300),
       (62, 900),
       (63, 2000),
       (64, 4500),
       (65, 9200),
       (66, 12000);
INSERT INTO level_fine (id, value)
VALUES (67, 140),
       (68, 700),
       (69, 2000),
       (70, 5500),
       (71, 7500),
       (72, 9500);
INSERT INTO level_fine (id, value)
VALUES (73, 140),
       (74, 700),
       (75, 2000),
       (76, 5500),
       (77, 7500),
       (78, 9500);
INSERT INTO level_fine (id, value)
VALUES (79, 160),
       (80, 800),
       (81, 2200),
       (82, 6000),
       (83, 8000),
       (84, 10000);
INSERT INTO level_fine (id, value)
VALUES (85, 180),
       (86, 900),
       (87, 2500),
       (88, 7000),
       (89, 8750),
       (90, 10500);
INSERT INTO level_fine (id, value)
VALUES (91, 180),
       (92, 900),
       (93, 2500),
       (94, 7000),
       (95, 8750),
       (96, 10500);
INSERT INTO level_fine (id, value)
VALUES (97, 200),
       (98, 1000),
       (99, 3000),
       (100, 7500),
       (101, 9250),
       (102, 11000);
INSERT INTO level_fine (id, value)
VALUES (103, 300),
       (104, 900),
       (105, 2000),
       (106, 4500),
       (107, 9200),
       (108, 12000);
INSERT INTO level_fine (id, value)
VALUES (109, 220),
       (110, 1100),
       (111, 3300),
       (112, 8000),
       (113, 9750),
       (114, 11500);
INSERT INTO level_fine (id, value)
VALUES (115, 220),
       (116, 1100),
       (117, 3300),
       (118, 8000),
       (119, 9750),
       (120, 11500);
INSERT INTO level_fine (id, value)
VALUES (121, 100),
       (122, 400),
       (123, 1000),
       (124, 1900),
       (125, 3100),
       (126, 5000);
INSERT INTO level_fine (id, value)
VALUES (127, 240),
       (128, 1200),
       (129, 3600),
       (130, 8500),
       (131, 10250),
       (132, 12000);
INSERT INTO level_fine (id, value)
VALUES (133, 260),
       (134, 1300),
       (135, 3900),
       (136, 9000),
       (137, 11000),
       (138, 12750);
INSERT INTO level_fine (id, value)
VALUES (139, 260),
       (140, 1300),
       (141, 3900),
       (142, 9000),
       (143, 11000),
       (144, 12750);
INSERT INTO level_fine (id, value)
VALUES (145, 280),
       (146, 1500),
       (147, 4500),
       (148, 10000),
       (149, 12000),
       (150, 14000);
INSERT INTO level_fine (id, value)
VALUES (151, 300),
       (152, 900),
       (153, 2000),
       (154, 4500),
       (155, 9200),
       (156, 12000);
INSERT INTO level_fine (id, value)
VALUES (157, 350),
       (158, 1750),
       (159, 5000),
       (160, 11000),
       (161, 13000),
       (162, 15000);
INSERT INTO level_fine (id, value)
VALUES (163, 500),
       (164, 2000),
       (165, 6000),
       (166, 14000),
       (167, 17000),
       (168, 20000);

INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (2, 6);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (4, 7),
       (4, 8),
       (4, 9),
       (4, 10),
       (4, 11),
       (4, 12);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (6, 13),
       (6, 14),
       (6, 15),
       (6, 16),
       (6, 17),
       (6, 18);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (7, 19),
       (7, 20),
       (7, 21),
       (7, 22),
       (7, 23),
       (7, 24);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (9, 25),
       (9, 26),
       (9, 27),
       (9, 28),
       (9, 29),
       (9, 30);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (10, 31),
       (10, 32),
       (10, 33),
       (10, 34),
       (10, 35),
       (10, 36);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (12, 37),
       (12, 38),
       (12, 39),
       (12, 40),
       (12, 41),
       (12, 42);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (13, 43),
       (13, 44),
       (13, 45),
       (13, 46),
       (13, 47),
       (13, 48);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (14, 49),
       (14, 50),
       (14, 51),
       (14, 52),
       (14, 53),
       (14, 54);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (15, 55),
       (15, 56),
       (15, 57),
       (15, 58),
       (15, 59),
       (15, 60);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (16, 61),
       (16, 62),
       (16, 63),
       (16, 64),
       (16, 65),
       (16, 66);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (17, 67),
       (17, 68),
       (17, 69),
       (17, 70),
       (17, 71),
       (17, 72);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (19, 73),
       (19, 74),
       (19, 75),
       (19, 76),
       (19, 77),
       (19, 78);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (20, 79),
       (20, 80),
       (20, 81),
       (20, 82),
       (20, 83),
       (20, 84);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (22, 85),
       (22, 86),
       (22, 87),
       (22, 88),
       (22, 89),
       (22, 90);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (24, 91),
       (24, 92),
       (24, 93),
       (24, 94),
       (24, 95),
       (24, 96);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (25, 97),
       (25, 98),
       (25, 99),
       (25, 100),
       (25, 101),
       (25, 102);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (26, 103),
       (26, 104),
       (26, 105),
       (26, 106),
       (26, 107),
       (26, 108);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (27, 109),
       (27, 110),
       (27, 111),
       (27, 112),
       (27, 113),
       (27, 114);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (28, 115),
       (28, 116),
       (28, 117),
       (28, 118),
       (28, 119),
       (28, 120);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (29, 121),
       (29, 122),
       (29, 123),
       (29, 124),
       (29, 125),
       (29, 126);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (30, 127),
       (30, 128),
       (30, 129),
       (30, 130),
       (30, 131),
       (30, 132);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (32, 133),
       (32, 134),
       (32, 135),
       (32, 136),
       (32, 137),
       (32, 138);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (33, 139),
       (33, 140),
       (33, 141),
       (33, 142),
       (33, 143),
       (33, 144);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (35, 145),
       (35, 146),
       (35, 147),
       (35, 148),
       (35, 149),
       (35, 150);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (36, 151),
       (36, 152),
       (36, 153),
       (36, 154),
       (36, 155),
       (36, 156);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (38, 157),
       (38, 158),
       (38, 159),
       (38, 160),
       (38, 161),
       (38, 162);
INSERT INTO company_card_fines(company_card_id, fines_id)
VALUES (40, 163),
       (40, 164),
       (40, 165),
       (40, 166),
       (40, 167),
       (40, 168);