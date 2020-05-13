const functions = require('firebase-functions');
const firebaseAdmin = require('firebase-admin');
firebaseAdmin.initializeApp()

exports.addUser = functions.https.onRequest((request, response) => {

    const text = request.query.text

    firebaseAdmin
        .database()
        .ref('/messages')
        .push({ text: "something great" })
        .then(() => response.json({
            message: "great",
            text
        }))
        .catch(() => response.json({
            message: "Not great"
        }));

});
