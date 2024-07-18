import { ActivityIndicator, Text, TextInput, View } from "react-native";
import { Controller, useForm } from "react-hook-form";
import { formStyles } from "../styles";
import FieldErrorMessage from "../form/FieldErrorMessage";
import { login } from "@/api/auth";
import { useState } from "react";
import { ErrorCode } from "@/api/error-types";
import { useAuthStore } from "@/store/auth-store";
import Button from "../Button";

type LoginForm = {
    email: string,
    password: string
}

type LoginFormProps = {
    onAuthSuccess: () => void
}

export default function LogInForm({ onAuthSuccess }: LoginFormProps) {
    const saveSessionToken = useAuthStore(state => state.saveSessionToken);

    const [loading, setLoading] = useState<boolean>(false);
    const [authError, setAuthError] = useState<ErrorCode | null | undefined>();

    const { 
        control, 
        handleSubmit,
        formState: { errors, isValid },
        getValues
    } = useForm<LoginForm>({
        mode: "all",
        defaultValues: {
            email: 'danmofo@gmail.com',
            password: 'password'
        }
    });

    async function handlePressLogInButton() {
        console.log('Logging in - valid? ', isValid);

        setAuthError(null);
        setLoading(true);
        
        const { success, errorCode, sessionToken } = await login(getValues());
        if (!success) {
            console.log('Failed to login', errorCode);
            setLoading(false);
            setAuthError(errorCode);
            return;
        }

        console.log('Login success!', sessionToken);
        setLoading(false);
        saveSessionToken(sessionToken!);
        onAuthSuccess();
    }
    
    return (
        <>
            <View>
                <Text style={formStyles.label}>Email address</Text>
                <Controller 
                    control={control}
                    name="email"
                    rules={{
                        required: 'Please enter an email address',
                        pattern: {
                            value: /.+@.+\..+/,
                            message: 'Please enter a valid email address'
                        }
                    }} 
                    render={({ field: { onChange, value } }) => (
                        <TextInput 
                            value={value}
                            onChangeText={onChange}
                            style={formStyles.input}
                        />
                    )}
                />
                <FieldErrorMessage fieldError={errors.email} />
            </View>
        
            <View>
                <Text style={formStyles.label}>Password</Text>
                <Controller 
                    control={control}
                    name="password"
                    rules={{
                        required: 'Please enter a password'
                    }} 
                    render={({ field: { onChange, value } }) => (
                        <TextInput 
                            value={value}
                            onChangeText={onChange}
                            style={formStyles.input}
                            secureTextEntry={true}
                        />
                    )}
                />
                <FieldErrorMessage fieldError={errors.password} />
            </View>

            {
                authError ?
                <FieldErrorMessage fieldError={{type: authError, message: 'Invalid credentials'}} /> :
                null
            }

            {
                loading ? 
                <ActivityIndicator size={32} /> :
                <Button title="Log in" onPress={handleSubmit(handlePressLogInButton)}/>
            }

        </>
    )
}