select SupplierID,CompanyName,ContactName,ContactTitle, Address as [Address.Street], City as [Address.City],
Region as [Address.Region], PostalCode as [Address.PostalCode], Country as [Address.Country], JSON_ARRAY(Phone) as Phone, Fax, HomePage
from Suppliers
for json path;

--Hacer esto con Intellitach para generar los json de los datos

--Tabla Products
select * from Products;

select 
    P.ProductID,
    P.ProductName, 
    S.SupplierID as [Suppliers.SupplierID],
    S.CompanyName as [Supplier.CompanyName],
    S.ContactName as [Supplier.ContactName], 
    C.CategoryID as [Categories.CategoryID],
    C.CategoryName as [Categories.CategoryName],
    P.QuantityPerUnit,
    P.UnitPrice,
    P.UnitsInStock,
    P.UnitsOnOrder,
    P.ReorderLevel,
    P.Discontinued
from Products P
inner join Categories C on C.CategoryID=P.CategoryID
inner join Suppliers S on S.SupplierID=P.SupplierID
for JSON PATH


------------------------------------------
-- Detalle de Ordenes
------------------------------------------

SELECT 

    O.OrderID,
    O.ProductID,
    P.ProductName,
    O.UnitPrice,
    O.Quantity,
    O.Discount,
   FORMAT((O.Quantity*O.UnitPrice-O.Discount*O.Quantity*O.UnitPrice),'N')  as subtotal
  FROM [Order Details] O
  join Products P on P.ProductID=O.ProductID
  where O.OrderID=10254
  for json path;

select 
  (
    select 
    O.OrderID,
    FORMAT((O.Quantity*O.UnitPrice-O.Discount*O.Quantity*O.UnitPrice),'N')  as subtotal,
    Ord.OrderDate,
    Ord.CustomerID
    from [Order Details] O
    join Orders Ord on O.OrderID=Ord.OrderID
    where Ord.CustomerID=OP.CustomerID
    for json path
  ) as Ordenes
from Orders OP
where OP.CustomerID='QUICK'
for json path

SELECT * FROM Orders 

select 
    O.OrderID,
    FORMAT((O.Quantity*O.UnitPrice-O.Discount*O.Quantity*O.UnitPrice),'N')  as subtotal,
    Ord.OrderDate,
    Ord.CustomerID
from [Order Details] O
join Orders Ord on O.OrderID=Ord.OrderID
for json path

------------------------------------------
-- Ordenes Generales
------------------------------------------  

SELECT 
    O.OrderID,

    O.CustomerID AS [Customer.CustomerID],
    C.CompanyName as [Customer.CompanyName],
    C.ContactName as [Customer.ContactName],

    O.EmployeeID as [Employees.EmployeeID],
    CONCAT(E.LastName,' ', E.FirstName) as [Employees.Name],

    O.OrderDate,
    O.RequiredDate,

    O.ShippedDate as [Shipper.Date],
    O.ShipVia as [Shipper.Via],
    O.Freight as [Shipper.Freight],
    O.ShipName as [Shipper.Name],
    O.ShipAddress as [Shipper.Address],
    O.ShipCity as [Shipper.City],
    O.ShipRegion as [Shipper.Region],
    O.ShipPostalCode as [Shipper.PostalCode],
    O.ShipCountry as [Shipper.Country],
    (
        SELECT 
        OD.ProductID as ProductID,
        P.ProductName as ProductName,
        OD.UnitPrice as UnitPrice,
        OD.Quantity as Quantity,
        FORMAT(OD.Discount,'N') as 'Discount',
        FORMAT((OD.Quantity*OD.UnitPrice-OD.Discount*OD.Quantity*OD.UnitPrice),'N') as 'SubTotal' 
        FROM [Order Details] OD
        join Products P on P.ProductID=OD.ProductID
        where OD.OrderID=O.OrderID
        for json path
    ) AS OrderDetails
  FROM Orders O
  join Employees E on E.EmployeeID=O.EmployeeID
  join Customers C on C.CustomerID=O.CustomerID
  where O.OrderID=10254
  for JSON path;


SELECT 
      O.OrderID,
      O.CustomerID as [Customer.CustomerID],
      C.CompanyName as [Customer.CompanyName],
      C.ContactName as [Customer.ContactName],

      O.EmployeeID as [Employee.EmployeeID],
      concat(E.LastName, ' ', E.FirstName) as [Employee.Name],

      O.OrderDate,
      O.RequiredDate,
      O.ShippedDate as [Shipper.Date],
      O.ShipVia as [Shipper.Via],
      O.Freight as [Shipper.Freight],
      O.ShipName as [Shipper.Name],
      O.ShipAddress as [Shipper.Address],
      O.ShipCity as [Shipper.City],
      O.ShipRegion as [Shipper.Region],
      O.ShipPostalCode as [Shipper.PostalCode],
      O.ShipCountry as [Shipper.Country],
      (
        SELECT 
            OD.ProductID as ProductID,
            P.ProductName as ProductName,
            OD.UnitPrice as UnitPrice,
            OD.Quantity as Quantity,
            format(OD.Discount, 'N') as Discount,
            format((OD.Quantity * OD.UnitPrice) - (OD.Quantity * OD.UnitPrice * OD.Discount), 'N') AS SubTotal
        FROM [Order Details] OD
        JOIN Products P ON P.ProductID = OD.ProductID
        WHERE OD.OrderID = O.OrderID 
        FOR JSON PATH
      ) as OrderDetails
  FROM Orders O
  JOIN Employees E ON O.EmployeeID = E.EmployeeID
  JOIN Customers C ON O.CustomerID = C.CustomerID
  FOR JSON PATH;  