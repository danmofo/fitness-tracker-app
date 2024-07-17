import { FieldError } from "react-hook-form"
import { formStyles } from "../styles";
import { Text } from "react-native";

type FieldErrorMessageProps = {
    fieldError: FieldError | undefined
}

export default function FieldErrorMessage({ fieldError }: FieldErrorMessageProps) {
    if (!fieldError) {
        return null;
    }
    return <Text style={formStyles.fieldError}>{fieldError.message}</Text>
}