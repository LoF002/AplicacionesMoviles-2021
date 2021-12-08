import { StyleSheet } from 'react-native';

export const globalStyles = StyleSheet.create({
    container: {
        alignContent: 'center',
        flex: 1,
        padding: 50,
    },
    title: {
        margin: 20,
        textAlign: 'center',
        fontSize: 30,
    },
    containerList: {
        margin: 50,
    },
    item: {
        backgroundColor: 'grey',
        color: 'white',
        borderRadius: 20,
        marginTop: 10,
        padding: 20,
        fontSize: 15,
    },
    centeredView: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
        marginTop: 22
    },
    modalView: {
        margin: 20,
        backgroundColor: "white",
        borderRadius: 20,
        padding: 40,
        alignItems: "center",
        shadowColor: "#000",
        shadowOffset: {
            width: 0,
            height: 2
        },
        shadowOpacity: 0.25,
        shadowRadius: 4,
        elevation: 5
    },
    button: {
        borderRadius: 20,
        padding: 10,
        elevation: 2
    },
    buttonOpen: {
        backgroundColor: "#FE9A00",
    },
    buttonAdd: {
        backgroundColor: "#FF2E39",
        marginTop: 20,
    },
    buttonClose: {
        borderRadius: 20,
        borderWidth: 1,
        backgroundColor: "grey",
        marginLeft: 140,
        marginRight: 140,
        marginBottom: 20,
        shadowColor: "#000",
        shadowOffset: {
            width: 0,
            height: 2
        },
        shadowOpacity: 0.25,
        shadowRadius: 4,
        elevation: 5
        
    },
    textStyle: {
        color: "white",
        fontWeight: "bold",
        textAlign: "center",
        fontSize: 20,
    },
    modalText: {
        marginBottom: 15,
        textAlign: "center"
    },
    input: {
        width: 300,
        borderWidth: 1,
        borderColor: '#000',
        padding: 10,
        margin: 10,
        fontSize: 16,
        borderRadius: 5,
    },
});