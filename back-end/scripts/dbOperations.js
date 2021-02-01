var dbconfig = require('../dbconfig');

const sql = require('mssql');
const config = require('../dbconfig');


async function getAllClients(){
    try {
        let pool =  await sql.connect(config);
        let clients = await pool.request().query("SELECT * FROM [DB_A6E8BF_eddiesri].[dbo].[CLIENTS]");
        return clients.recordsets[0];
    } catch (error) {
        return (error)
    }
}
async function getOneClient(client_id){
    try {
        let pool =  await sql.connect(config);
        let clients = await pool.request()
        .query("SELECT * FROM [DB_A6E8BF_eddiesri].[dbo].[CLIENTS] WHERE CLIENT_UID ="+ client_id );
        return clients.recordsets[0];
    } catch (error) {
        return (error)
    }
}
async function createClients(client){
    try {
        const query =   " INSERT INTO  [DB_A6E8BF_eddiesri].[dbo].[CLIENTS] " +
                        "   ([CLIENT_NAME] ,[CLIENT_TYPE] ,[COMPANY_CODE]  ,[CONTACT_NUMBER] ,[EMAIL], [SP_UID])"  +
                        " VALUES " +
                        "   ('"+ client['CLIENT_NAME'] +"', '" + client['CLIENT_TYPE'] +"', " + client['COMPANY_CODE'] +", " + client['CONTACT_NUMBER'] +", '" + client['EMAIL'] +"', '" + client['SP_UID'] +"' ) " 
        let pool =  await sql.connect(config);
        let insertPool = await pool.request().query(query);
        return insertPool;
    } catch (error) {
        return (error)
    }
}
async function updateClients(clientData,client_id){
    try {
        const query = "UPDATE  [DB_A6E8BF_eddiesri].[dbo].[CLIENTS] SET"
                    + " CLIENT_NAME = '" + clientData['CLIENT_NAME'] + "', "
                    + " CLIENT_TYPE = '" + clientData['CLIENT_TYPE'] + "', "
                    + " COMPANY_CODE = " + clientData['COMPANY_CODE'] + ", "
                    + " CONTACT_NUMBER = " + clientData['CONTACT_NUMBER'] + ", "
                    + " EMAIL = '" + clientData['EMAIL'] + "' "
                    + " WHERE CLIENT_UID ="+ client_id 
        let pool =  await sql.connect(config);
        let insertPool = await pool.request().query(query);

        return insertPool;
    } catch (error) {
        return (error)
    }
}
async function deleteClients(client_id){
    try {
        let pool =  await sql.connect(config);
        let insertPool = await pool.request()
        .query("DELETE FROM [DB_A6E8BF_eddiesri].[dbo].[CLIENTS] WHERE CLIENT_UID="+ client_id );

        return insertPool;
    } catch (error) {
        return (error)
    }
}


async function getAllSalesPersons(){
    try {
        let pool =  await sql.connect(config);
        let clients = await pool.request().query("SELECT [SP_UID], [SP_NAME] ,[SP_CONTACT_NUMBER] ,[EMAIL] FROM [DB_A6E8BF_eddiesri].[dbo].[SALES_PERSON]");
        return clients.recordsets[0];
    } catch (error) {
        return (error)
    }
}


async function getOneSalesPerson(sp_id){
    try {
        let pool =  await sql.connect(config);
        let clients = await pool.request()
        .query("SELECT [SP_UID], [SP_NAME] ,[SP_CONTACT_NUMBER] ,[EMAIL] FROM [DB_A6E8BF_eddiesri].[dbo].[SALES_PERSON] WHERE SP_UID ="+ sp_id + "");
        return clients.recordsets[0];
    } catch (error) {
        return (error)
    }
}

async function getUserSalesPerson(email,password){
    try {
        let pool =  await sql.connect(config);
        let clients = await pool.request()
        .query("SELECT [SP_UID], [SP_NAME] ,[SP_CONTACT_NUMBER] ,[EMAIL] FROM [DB_A6E8BF_eddiesri].[dbo].[SALES_PERSON] WHERE EMAIL ='"+ email + "' AND PASSWORD_CODE ='" + password + "'");
        return clients.recordsets[0];
    } catch (error) {
        return (error)
    }
}

async function createSalesPerson(client){
    try {
        const query =   " INSERT INTO  [DB_A6E8BF_eddiesri].[dbo].[SALES_PERSON] " +
                        "   ([SP_NAME] ,[SP_CONTACT_NUMBER] ,[EMAIL]  ,[PASSWORD_CODE])"  +
                        " VALUES " +
                        "   ('"+ client['SP_NAME'] +"', " + client['SP_CONTACT_NUMBER'] +", '" + client['EMAIL'] +"' , '" + client['PASSWORD_CODE'] +"' ) " 
        let pool =  await sql.connect(config);
        let insertPool = await pool.request().query(query);
        return insertPool;
    } catch (error) {
        return (error)
    }
}

async function updateSalesPerson(salesPersonData,salesPersonId){
    try {
        const query = "UPDATE  [DB_A6E8BF_eddiesri].[dbo].[SALES_PERSON] SET"
                    + " SP_NAME = '" + salesPersonData['SP_NAME'] + "', "
                    + " SP_CONTACT_NUMBER = " + salesPersonData['SP_CONTACT_NUMBER'] + ", "
                    + " EMAIL = '" + salesPersonData['EMAIL'] + "' "
                    + " WHERE SP_UID ="+ salesPersonId ;
        let pool =  await sql.connect(config);
        let insertPool = await pool.request().query(query);

        return insertPool;
    } catch (error) {
        return (error)
    }
}



async function deleteSalesPerson(salesPersonId){
    try {
        let pool =  await sql.connect(config);
        let insertPool2 = await pool.request().query("DELETE FROM [DB_A6E8BF_eddiesri].[dbo].[CLIENTS] WHERE SP_UID='"+ salesPersonId + "'");
        let insertPool = await pool.request().query("DELETE FROM [DB_A6E8BF_eddiesri].[dbo].[SALES_PERSON] WHERE SP_UID='"+ salesPersonId + "'");

        return insertPool;
    } catch (error) {
        return (error)
    }
}

module.exports = {
    getAllClients: getAllClients,
    getOneClient: getOneClient,
    createClients: createClients,
    updateClients: updateClients,
    deleteClients: deleteClients,
    getAllSalesPersons : getAllSalesPersons,
    getOneSalesPerson : getOneSalesPerson,
    getUserSalesPerson : getUserSalesPerson,
    createSalesPerson : createSalesPerson,
    updateSalesPerson : updateSalesPerson,
    deleteSalesPerson : deleteSalesPerson
}