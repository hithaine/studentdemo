CREATE DATABASE Human
GO

USE Human
GO

--create table
CREATE TABLE [dbo].[HumanType](
	[typeid] [int] IDENTITY(1,1) NOT NULL primary key,
	[typename] [VARCHAR](50)
)
GO

CREATE TABLE [dbo].[Human] (
    [humanid] [int] IDENTITY(1,1) NOT NULL primary key,
    [humanname] [VARCHAR](50),
    [humandob] [DATE] NULL,
    [humangender] [VARCHAR](10)	NULL,
    [typeid] [int] NOT NULL references [dbo].[HumanType](typeid)
)
GO

--insert data
INSERT [dbo].[HumanType] ([typename]) VALUES (N'student')
INSERT [dbo].[HumanType] ([typename]) VALUES (N'teacher')
INSERT [dbo].[HumanType] ([typename]) VALUES (N'worker')
GO

INSERT [dbo].[Human] ([humanname], [humandob], [humangender], [typeid]) VALUES (N'Steve', N'2020-09-09', N'Male', 1)
INSERT [dbo].[Human] ([humanname], [humandob], [humangender], [typeid]) VALUES (N'Elon', N'2019-09-09', N'Female', 2)
INSERT [dbo].[Human] ([humanname], [humandob], [humangender], [typeid]) VALUES (N'Musk', N'2020-09-09', N'Male', 3)
INSERT [dbo].[Human] ([humanname], [humandob], [humangender], [typeid]) VALUES (N'Melon', N'2020-08-09', N'Male', 1)
INSERT [dbo].[Human] ([humanname], [humandob], [humangender], [typeid]) VALUES (N'Kais', N'2020-07-08', N'Female', 3)
GO

--SELECT h.humanid, h.humanname, h. humandob, h. humangender, ht.typeid, ht.typename FROM [Human] h INNER JOIN HumanType ht ON h.typeid = ht.typeid