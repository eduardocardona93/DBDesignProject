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
        .query("SELECT * FROM [DB_A6E8BF_eddiesri].[dbo].[CLIENTS] WHERE CLIENT_UID ='"+ client_id + "'");
        return clients.recordsets[0];
    } catch (error) {
        return (error)
    }
}

async function createClients(client){
    try {
        const query = " INSERT INTO  [DB_A6E8BF_eddiesri].[dbo].[CLIENTS] " +
        " ([CLIENT_NAME] ,[CLIENT_TYPE] ,[COMPANY_CODE]  ,[CONTACT_NUMBER] ,[EMAIL])"  +
        " VALUES " +
        " ('"+ client['CLIENT_NAME'] +"', '" + client['CLIENT_TYPE'] +"', " + client['COMPANY_CODE'] +", " + client['CONTACT_NUMBER'] +", '" + client['EMAIL'] +"' ) " 
        console.log(query)
        let pool =  await sql.connect(config);
        let insertPool = await pool.request()
        .query(query);

        return insertPool;
    } catch (error) {
        return (error)
    }
}

async function updateClients(clientData,client_id){
    try {
        let pool =  await sql.connect(config);
        let insertPool = await pool.request()
        .query("UPDATE  [DB_A6E8BF_eddiesri].[dbo].[CLIENTS] SET"
            + " CLIENT_NAME = '" + clientData['CLIENT_NAME'] + "', "
            + " CLIENT_TYPE = '" + clientData['CLIENT_TYPE'] + "', "
            + " COMPANY_CODE = " + clientData['COMPANY_CODE'] + ", "
            + " CONTACT_NUMBER = " + clientData['CONTACT_NUMBER'] + ", "
            + " EMAIL = '" + clientData['EMAIL'] + "' "
            + " WHERE CLIENT_UID ="+ client_id );

        return insertPool;
    } catch (error) {
        return (error)
    }
}

async function deleteClients(client_id){
    try {
        let pool =  await sql.connect(config);
        let insertPool = await pool.request()
        .query("DELETE FROM [DB_A6E8BF_eddiesri].[dbo].[CLIENTS] WHERE CLIENT_UID='"+ client_id + "'");

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
}