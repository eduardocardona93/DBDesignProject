'use strict';
const dbOperations = require('./scripts/dbOperations');
var express = require('express');
var requestsHandler = require('./scripts/requestsHandlers');
var bodyParser = require('body-parser');
var multer = require('multer');
var upload = multer();
var app = express();
var port = process.env.PORT || 3000;
// for parsing application/json
app.use(bodyParser.json());

// for parsing application/xwww-
app.use(bodyParser.urlencoded({ extended: true }));
//form-urlencoded

// for parsing multipart/form-data
app.use(upload.array());
app.use(express.static('public'));


app.use(express.json());

// url for the api
var api = "/api/v1";

/** urls for funcions rediceting to dbOperations **/

//******************* Clients Resources *************************/
// gets specific client information
app.get(api + '/clients/:clientId', (req, res) => {
    dbOperations.getOneClient(req, res);
})
// creating a client
app.post(api + '/clients', (req, res) => {
    dbOperations.createClients(req, res);
})
// updating an existing client
app.post(api + '/clients/:clientId', (req, res) => {
    dbOperations.updateClients(req, res);
})
// deleting an existing client
app.delete(api + '/clients/:clientId', (req, res) => {
    dbOperations.deleteClients(req, res);
})

//******************* Sales person Resources *************************/

// gets all the sales person registers
app.get(api + '/salesPerson', (req, res) => {
    dbOperations.getAllSalesPersons(req, res);
})


// gets an specific sales person information
app.get(api + '/salesPerson/:salesPersonId', (req, res) => {
    dbOperations.getOneSalesPerson(req, res);
})  

// gets all clients from a sales person
app.get(api + '/salesPerson_clients/:salesPersonId', (req, res) => {
    dbOperations.getAllSalesPersonClients(req, res);
})  

// gets the user data if has the valid credentials (email, password)
app.post(api + '/login', (req, res) => {
    dbOperations.getUserSalesPerson(req, res);
})

// creates a new sales person
app.post(api + '/salesPerson', (req, res) => {
    dbOperations.createSalesPerson(req, res);
})

// updates the sales person data
app.post(api + '/salesPerson/:salesPersonId', (req, res) => {

    dbOperations.updateSalesPerson(req, res);
})
//deletes the sales person
app.delete(api + '/salesPerson/:salesPersonId', (req, res) => {
    dbOperations.deleteSalesPerson(req, res);
})
// redirect in case of not found
app.get('/*', function(req, res) {
    requestsHandler.testRequest(req, res);
});
//start service
app.listen(port, () => {
    console.log('Server Running: ' + port)
})