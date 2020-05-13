const functions = require('firebase-functions');
const firebaseAdmin = require('firebase-admin');
firebaseAdmin.initializeApp


exports.addUser = functions.https.onRequest((request, response) => {

    firebaseAdmin
        .database()
        .ref('/messages')
        .push({ text: request.query.text })
        .then(() => response.json({
            message: "great",
            text
        }))
        .catch(() => response.json({
            message: "Not great"
        }));

});
