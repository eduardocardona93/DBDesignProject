var dbconfig = require('../dbconfig');

const sql = require('mssql');
const config = require('../dbconfig');


async function getAllPruebas(){
    try {
        let pool =  await sql.connect(config);
        let pruebas = await pool.request().query("SELECT [CUST_ID] ,[DOB] FROM [DB_A6E8BF_eddiesri].[dbo].[PRUEBA]");
        return pruebas.recordsets[0];
    } catch (error) {
        return (error)
    }
}

async function getOnePrueba(cust_id){
    try {
        let pool =  await sql.connect(config);
        let pruebas = await pool.request()
        .query("SELECT [CUST_ID] ,[DOB] FROM [DB_A6E8BF_eddiesri].[dbo].[PRUEBA] WHERE CUST_ID ='"+ cust_id + "'");
        return pruebas.recordsets[0];
    } catch (error) {
        return (error)
    }
}

async function createPruebas(prueba){
    try {
        let pool =  await sql.connect(config);
        let insertPool = await pool.request()
        .query("INSERT INTO  [DB_A6E8BF_eddiesri].[dbo].[PRUEBA] (CUST_ID,DOB) VALUES ("+ prueba['CUST_ID'] +", '" + prueba['DOB'] +"')");

        return insertPool;
    } catch (error) {
        return (error)
    }
}

async function updatePruebas(prueba,cust_id){
    try {
        let pool =  await sql.connect(config);
        let insertPool = await pool.request()
        .query("UPDATE  [DB_A6E8BF_eddiesri].[dbo].[PRUEBA] SET"
            + " DOB = '" + prueba['DOB'] + "'"
            + " WHERE CUST_ID ='"+ cust_id + "'");

        return insertPool;
    } catch (error) {
        return (error)
    }
}

async function deletePruebas(cust_id){
    try {
        let pool =  await sql.connect(config);
        let insertPool = await pool.request()
        .query("DELETE FROM [DB_A6E8BF_eddiesri].[dbo].[PRUEBA] WHERE CUST_ID='"+ cust_id + "'");

        return insertPool;
    } catch (error) {
        return (error)
    }
}

module.exports = {
    getAllPruebas: getAllPruebas,
    getOnePrueba: getOnePrueba,
    createPruebas: createPruebas,
    updatePruebas: updatePruebas,
    deletePruebas: deletePruebas,
}