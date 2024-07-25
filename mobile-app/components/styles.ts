import { StyleSheet } from "react-native";

export const formStyles = StyleSheet.create({
    label: {
        marginBottom: 8,
        fontWeight: 'bold'
    },
    fieldError: {
        color: 'red'
    },
    inputContainer: {
        marginBottom: 20
    },
    input: {
        borderWidth: 1,
        borderColor: '#CCC',
        paddingHorizontal: 20,
        paddingVertical: 10,
        fontSize: 18
    }
});