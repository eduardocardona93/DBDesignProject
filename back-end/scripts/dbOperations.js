// requires definition
const sql = require('mssql');
const config = require('../dbconfig');

//****************** Client Operations **************************/
// gets specific client information
async function getOneClient(req, res){
    var clientId = req.params.clientId;
    try {
        // init sql connection
        let pool =  await sql.connect(config);
        //execute query
        let clients = await pool.request()
        .query("SELECT * FROM [DB_A6E8BF_eddiesri].[dbo].[CLIENTS] WHERE CLIENT_UID ="+ clientId );
        // if success response
        if(clients && clients.recordsets && clients.recordsets[0] && clients.recordsets[0].length > 0 ){
            res.json({
                message:"Success on Client #"+ clientId +" Request",
                data : clients.recordsets[0][0]
            })
        }else{
            // if not possible to get the desired response
            res.status(500).json({
                errorMessage:"Internal Server Error",
                errorDescription :"Client id: " + clientId + " not found"
            })
        }
    } catch (error) {
        // if error on the process
        res.status(500).json({
            errorMessage:"Internal Server Error",
            errorDescription :"Problem Excecuting query for clients",
            errorData: error
        })
    }
}
// creating a client
async function createClients(req, res){
    var ClientData = {...req.body};
    try {
        //query to do the transaction
        const query =   " INSERT INTO  [DB_A6E8BF_eddiesri].[dbo].[CLIENTS] " +
                        "   ([CLIENT_NAME] ,[CLIENT_TYPE] ,[COMPANY_CODE]  ,[CONTACT_NUMBER] ,[EMAIL], [SP_UID])"  +
                        " VALUES " +
                        "   ('"+ ClientData['CLIENT_NAME'] +"', '" + ClientData['CLIENT_TYPE'] +"', " + ClientData['COMPANY_CODE'] +", " + ClientData['CONTACT_NUMBER'] +", '" + ClientData['EMAIL'] +"', '" + ClientData['SP_UID'] +"' ) " 
                        // init sql connection
        let pool =  await sql.connect(config);
        //execute query
        let clientPool = await pool.request().query(query);
        // if success response
        if(clientPool && clientPool.rowsAffected && clientPool.rowsAffected[0] && clientPool.rowsAffected[0] > 0){
            res.json({
                message:"Success on creating Client: " + ClientData['CLIENT_NAME'],
                data : clientPool
            })
        }else{
            // if not possible to get the desired response
            res.status(500).json({
                errorMessage:"Internal Server Error",
                errorDescription :"Problem on creating Client: " + ClientData['CLIENT_NAME']
            })
        }
    } catch (error) {
        // if error on the process
        res.status(500).json({
            errorMessage:"Internal Server Error",
            errorDescription :"Problem on creating Client: " + ClientData['CLIENT_NAME'],
            errorData: error
        })
    }
}
// updating an existing client
async function updateClients(req, res){
    var clientId = req.params.clientId;
    var clientData = {...req.body};
    try {
        //query to do the transaction
        const query = "UPDATE  [DB_A6E8BF_eddiesri].[dbo].[CLIENTS] SET"
                    + " CLIENT_NAME = '" + clientData['CLIENT_NAME'] + "', "
                    + " CLIENT_TYPE = '" + clientData['CLIENT_TYPE'] + "', "
                    + " COMPANY_CODE = " + clientData['COMPANY_CODE'] + ", "
                    + " CONTACT_NUMBER = " + clientData['CONTACT_NUMBER'] + ", "
                    + " EMAIL = '" + clientData['EMAIL'] + "' "
                    + " WHERE CLIENT_UID ="+ clientId 
                    // init sql connection
        let pool =  await sql.connect(config);
        //execute query
        let clientPool = await pool.request().query(query);
        // if success response
        if(clientPool && clientPool.rowsAffected && clientPool.rowsAffected[0] && clientPool.rowsAffected[0] > 0){
            res.json({
                message:"Success on updating Client #" + clientId,
                data : clientPool
            })
        }else{
            // if not possible to get the desired response
            res.status(500).json({
                errorMessage:"Internal Server Error",
                errorDescription :"Problem on updating Client #" + clientId,
            })
        }
    } catch (error) {
        // if error on the process
        res.status(500).json({
            errorMessage:"Internal Server Error",
            errorDescription :"Problem on updating Client #" + clientId,
            errorData: error
        })
    }
}
// deleting an existing client
async function deleteClients(req, res){
    var clientId = req.params.clientId;
    try {
        // init sql connection
        let pool =  await sql.connect(config);
        //execute query
        let clientPool = await pool.request()
        .query("DELETE FROM [DB_A6E8BF_eddiesri].[dbo].[CLIENTS] WHERE CLIENT_UID="+ clientId );
        // if success response
        if(clientPool && clientPool.rowsAffected && clientPool.rowsAffected[0] && clientPool.rowsAffected[0] > 0){
            res.json({
                message:"Success on deleting Client #" + clientId,
                data : clientPool
            })
        }else{
            // if not possible to get the desired response
            res.status(500).json({
                errorMessage:"Internal Server Error",
                errorDescription :"Problem on deleting Client #" + clientId,
            })
        }
    } catch (error) {
        // if error on the process
        res.status(500).json({
            errorMessage:"Internal Server Error",
            errorDescription :"Problem on deleting Client #" + clientId,
            errorData: error
        })
    }

}


//******************* Sales person Operations *************************/

// gets all the sales person registers
async function getAllSalesPersons(req, res){
    try {
        // init sql connection
        let pool =  await sql.connect(config);
        //execute query
        let salesPerson = await pool.request().query("SELECT [SP_UID], [SP_NAME] ,[SP_CONTACT_NUMBER] ,[EMAIL] FROM [DB_A6E8BF_eddiesri].[dbo].[SALES_PERSON]");
        // if success response
        if(salesPerson && salesPerson.recordsets &&  salesPerson.recordsets[0]){
            res.json({
                message:"Success on sales persons Request",
                data : salesPerson.recordsets[0]
            })
        }else{
            // if not possible to get the desired response
            res.status(500).json({
                errorMessage:"Internal Server Error",
                errorDescription :"Problem Excecuting query for sales persons"
            })
        }
    } catch (error) {
        // if error on the process
        res.status(500).json({
            errorMessage:"Internal Server Error",
            errorDescription :"Problem Excecuting query for all sales persons",
            errorData: error
        })
    }
}
// gets an specific sales person information
async function getOneSalesPerson(req, res){
    var salesPersonId = req.params.salesPersonId;
    try {
        // init sql connection
        let pool =  await sql.connect(config);
        //execute query
        let salesPerson = await pool.request()
        .query("SELECT [SP_UID], [SP_NAME] ,[SP_CONTACT_NUMBER] ,[EMAIL] FROM [DB_A6E8BF_eddiesri].[dbo].[SALES_PERSON] WHERE SP_UID ="+ salesPersonId + "");
        // if success response
        if(salesPerson && salesPerson.recordsets &&  salesPerson.recordsets[0] && salesPerson.recordsets[0].length > 0 ){
            res.json({
                message:"Success on Sales Person #"+ salesPersonId +" Request",
                data : salesPerson.recordsets[0][0]
            })
        }else{
            // if not possible to get the desired response
            res.status(500).json({
                errorMessage:"Internal Server Error",
                errorDescription :"Sales Person id: " + salesPersonId + " not found"
            })
        }
    } catch (error) {
        // if error on the process
        res.status(500).json({
            errorMessage:"Internal Server Error",
            errorDescription :"Problem Excecuting query for Sales Person",
            errorData: error
        })
    }
}
// gets all clients from a sales person
async function getAllSalesPersonClients(req, res){
    var salesPersonId = req.params.salesPersonId;
    try {
        // init sql connection
        let pool =  await sql.connect(config);
        //execute query
        let clients = await pool.request().query("SELECT * FROM [DB_A6E8BF_eddiesri].[dbo].[CLIENTS] WHERE SP_UID = " + salesPersonId);
        // if success response
        if(clients && clients.recordsets ){
            res.json({
                message:"Success on Clients Request",
                data : clients.recordsets[0]
            })
        }else{
            // if not possible to get the desired response
            res.status(500).json({
                errorMessage:"Internal Server Error",
                errorDescription :"Problem Excecuting query for clients"
            })
        }
    } catch (error) {
        // if error on the process
        res.status(500).json({
            errorMessage:"Internal Server Error",
            errorDescription :"Problem Excecuting query for all clients",
            errorData: error
        })
    }
}
// gets the user data if has the valid credentials (email, password)
async function getUserSalesPerson(req, res){
    var email = req.body.email;
    var password = req.body.password;
    try {
        // init sql connection
        let pool =  await sql.connect(config);
        //execute query
        let salesPerson = await pool.request().query("SELECT [SP_UID], [SP_NAME] ,[SP_CONTACT_NUMBER] ,[EMAIL] FROM [DB_A6E8BF_eddiesri].[dbo].[SALES_PERSON] WHERE EMAIL ='"+ email + "' AND PASSWORD_CODE ='" + password + "'");
        // if success response
        if(salesPerson && salesPerson.recordsets &&  salesPerson.recordsets[0] && salesPerson.recordsets[0].length > 0 ){
            res.json({
                message:"Login successful",
                data : salesPerson.recordsets[0][0]
            })
        }else{
            // if not possible to get the desired response
            res.status(500).json({
                errorMessage:"Error on user login",
                errorDescription :"Wrong credentials for " + email + ", please verify your email and password and try again"
            })
        }
    } catch (error) {
        // if error on the process
        res.status(500).json({
            errorMessage:"Internal Server Error",
            errorDescription :"Problem on login for " + email,
            errorData: error
        })
    }
}
// creates a new sales person
async function createSalesPerson(req, res){
    var salesPersonData = {...req.body};
    try {
        //query to do the transaction
        const query =   " INSERT INTO  [DB_A6E8BF_eddiesri].[dbo].[SALES_PERSON] " +
                        "   ([SP_NAME] ,[SP_CONTACT_NUMBER] ,[EMAIL]  ,[PASSWORD_CODE])"  +
                        " VALUES " +
                        "   ('"+ salesPersonData['SP_NAME'] +"', " + salesPersonData['SP_CONTACT_NUMBER'] +", '" + salesPersonData['EMAIL'] +"' , '" + salesPersonData['PASSWORD_CODE'] +"' ) " 
                        // init sql connection
        let pool =  await sql.connect(config);
        //execute query
        let salespersonPool = await pool.request().query(query);
        // if success response
        if(salespersonPool && salespersonPool.rowsAffected && salespersonPool.rowsAffected[0] && salespersonPool.rowsAffected[0] > 0){
            res.json({
                message:"Success on creating sales person: " + salesPersonData['SP_NAME'],
                data : salespersonPool
            })
        }else{
            // if not possible to get the desired response
            res.status(500).json({
                errorMessage:"Internal Server Error",
                errorDescription :"Problem on creating sales person: " + salesPersonData['SP_NAME']
            })
        }
    } catch (error) {
        // if error on the process
        res.status(500).json({
            errorMessage:"Internal Server Error",
            errorDescription :"Problem on creating sales person: " + salesPersonData['SP_NAME'],
            errorData: error
        })
    }
}
// updates the sales person data
async function updateSalesPerson(req, res){
    var salesPersonId = req.params.salesPersonId;
    var salesPersonData = {...req.body};
    try {
        //query to do the transaction
        const query = "UPDATE  [DB_A6E8BF_eddiesri].[dbo].[SALES_PERSON] SET"
                    + " SP_NAME = '" + salesPersonData['SP_NAME'] + "', "
                    + " SP_CONTACT_NUMBER = " + salesPersonData['SP_CONTACT_NUMBER'] + ", "
                    + " EMAIL = '" + salesPersonData['EMAIL'] + "' "
                    + " WHERE SP_UID ="+ salesPersonId ;
                    // init sql connection
        let pool =  await sql.connect(config);
        //execute query
        let salespersonPool = await pool.request().query(query);
        // if success response
        if(salespersonPool && salespersonPool.rowsAffected && salespersonPool.rowsAffected[0] && salespersonPool.rowsAffected[0] > 0){
            res.json({
                message:"Success on updating sales person #" + salesPersonId,
                data : salespersonPool
            })
        }else{
            // if not possible to get the desired response
            res.status(500).json({
                errorMessage:"Internal Server Error",
                errorDescription :"Problem on updating sales person #" + salesPersonId,
            })
        }
    } catch (error) {
        // if error on the process
        res.status(500).json({
            errorMessage:"Internal Server Error",
            errorDescription :"Problem on updating sales person #" + salesPersonId,
            errorData: error
        })
    }
}
//deletes the sales person
async function deleteSalesPerson(req, res){
    var salesPersonId = req.params.salesPersonId;
    try {
        // init sql connection
        let pool =  await sql.connect(config);
        //execute query
        let salespersonPool2 = await pool.request().query("DELETE FROM [DB_A6E8BF_eddiesri].[dbo].[CLIENTS] WHERE SP_UID='"+ salesPersonId + "'");
        let salespersonPool = await pool.request().query("DELETE FROM [DB_A6E8BF_eddiesri].[dbo].[SALES_PERSON] WHERE SP_UID='"+ salesPersonId + "'");
        // if success response
        if(salespersonPool && salespersonPool.rowsAffected && salespersonPool.rowsAffected[0] && salespersonPool.rowsAffected[0] > 0){
            res.json({
                message:"Success on deleting sales person #" + salesPersonId,
                data : salespersonPool
            })
        }else{
            // if not possible to get the desired response
            res.status(500).json({
                errorMessage:"Internal Server Error",
                errorDescription :"Problem on deleting sales person #" + salesPersonId,
            })
        }
    } catch (error) {
        // if error on the process
        res.status(500).json({
            errorMessage:"Internal Server Error",
            errorDescription :"Problem on deleting sales person #" + salesPersonId,
            errorData: error
        })
    }
}


// exports
module.exports = {
    getOneClient: getOneClient,
    createClients: createClients,
    updateClients: updateClients,
    deleteClients: deleteClients,
    getAllSalesPersons : getAllSalesPersons,
    getAllSalesPersonClients: getAllSalesPersonClients,
    getOneSalesPerson : getOneSalesPerson,
    getUserSalesPerson : getUserSalesPerson,
    createSalesPerson : createSalesPerson,
    updateSalesPerson : updateSalesPerson,
    deleteSalesPerson : deleteSalesPerson
}