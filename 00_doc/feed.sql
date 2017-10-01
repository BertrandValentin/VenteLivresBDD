INSERT INTO `author` (`IdAuthor`, `FirstName`, `LastName`, `Birthday`, `Nationality`, `IsActive`) VALUES
(1, 'NIETZSCHE', 'Friedrich', '1844-10-15', 'prussien', 1),
(2, 'LACAN', 'Jacques', '1901-04-13', 'french', 1),
(3, 'DHAMMA', 'Rewata', '1924-12-04', 'burmese', 1);

INSERT INTO `editor` (`IdEditor`, `EditorName`, `IsActive`) VALUES
(1, 'Philology and mind', 1),
(2, 'Heaven and hell', 1),
(3, 'Suites', 1);

INSERT INTO `category` (`IdCategory`, `CategoryName`, `IsActive`) VALUES
(1, 'Philosophy', 1),
(2, 'Roman', 1),
(3, 'Psychanalysis', 1);

INSERT INTO `book` (`IdBook`, `title`, `Author_IdAuthor`, `Category_IdCategory`, `Editor_IdEditor`, `Price`, `IsActive`) VALUES
(1, 'Ecce homo', 1, 1, 1, 16, 1),
(2, 'Le mot d''esprit dans sa relation ра l''inconscient', 1, 1, 1, 8, 1),
(3, 'More human than human', 1, 1, 1, 24, 1);

INSERT INTO `locality` (`IdLocality`, `ZipCode`, `City`) VALUES
(1, '1348', 'Louvain-la-Neuve');
(2, '5660', 'Couvin');
(1, '6000', 'Charleroi');

INSERT INTO `order` (`IdOrder`, `User_IdUser`) VALUES
(1, 1);

INSERT INTO `orderline` (`IdOrderLine`, `Order_IdOrder`, `Book_IdBook`, `Quantity`) VALUES
(1, 1, 1, 1);

INSERT INTO `role` (`IdRole`, `RoleName`, `IsActive`) VALUES
(1, 'admin', 1),
(2, 'user', 1),
(3, 'moderator', 1);

INSERT INTO `user` (`IdUser`, `Role_IdRole`, `Locality_IdLocality`, `FirstName`, `LastName`, `Birthday`, `Email`, `PersonalPhone`, `Street`, `Number`, `Box`, `Country`, `Password`, `IsActive`) VALUES
(1, 1, 1, 'e', 'e', '1985-12-25', 'e@mailinator.com', '010000000', 'e', 1, '1', 'be', 'e', 1),
(2, 2, 1, 'a', 'a', '1984-01-01', 'a@mailinator.com', '000000001', 'a', 1, '1', 'be', 'a', 1),
(3, 3, 1, 'b', 'b', '1983-02-02', 'a@mailinator.com', '000000010', 'b', 1, '1', 'be', 'b', 1);
