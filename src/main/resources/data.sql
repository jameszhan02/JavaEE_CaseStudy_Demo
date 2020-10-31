INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email)VALUES ('123 Maple
St','London','On', 'N1N-1N1','(555)555-5555','Trusted','ABC Supply Co.','abc@supply.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email) VALUES ('543
Sycamore Ave','Toronto','On', 'N1P-1N1','(999)555-5555','Trusted','Big Bills
Depot','bb@depot.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email) VALUES ('922 Oak
St','London','On', 'N1N-1N1','(555)555-5599','Un Trusted','Shady Sams','ss@underthetable.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email) VALUES ('922 Oak
St','London','On', 'N1N-1N1','(555)555-5599','Un Trusted','Sheng Zhan','sz@underthetable.com');

INSERT INTO Product (Id, VendorId, Name, CostPrice, msrp, rop, eoq, qoh, qoo, QrCode, QrCodeTxt) VALUES('14X45', 1, 'Gadget', 359.99, 329.98, 5, 5, 10, 0, '', '');
INSERT INTO Product (Id, VendorId, Name, CostPrice, msrp, rop, eoq, qoh, qoo, QrCode, QrCodeTxt) VALUES('14X46', 1, 'GadgetSec', 359.99, 329.98, 5, 5, 10, 0, '', '');
INSERT INTO Product (Id, VendorId, Name, CostPrice, msrp, rop, eoq, qoh, qoo, QrCode, QrCodeTxt) VALUES('14X47', 1, 'GadgetTh', 359.99, 329.98, 5, 5, 10, 0, '', '');
INSERT INTO Product (Id, VendorId, Name, CostPrice, msrp, rop, eoq, qoh, qoo, QrCode, QrCodeTxt) VALUES('14X48', 1, 'GadgetFith', 359.99, 329.98, 5, 5, 10, 0, '', '');
INSERT INTO Product (Id, VendorId, Name, CostPrice, msrp, rop, eoq, qoh, qoo, QrCode, QrCodeTxt) VALUES('15X01', 2, 'WhatEverItis', 359.99, 329.98, 5, 5, 10, 0, '', '');
INSERT INTO Product (Id, VendorId, Name, CostPrice, msrp, rop, eoq, qoh, qoo, QrCode, QrCodeTxt) VALUES('15X02', 2, 'Fresh Air', 10.00,0.00, 5, 5, 10, 0, '', '');
INSERT INTO Product (Id, VendorId, Name, CostPrice, msrp, rop, eoq, qoh, qoo, QrCode, QrCodeTxt) VALUES('16X01', 3, 'Fresh Meat', 100.89,77.89, 5, 5, 10, 0, '', '');

