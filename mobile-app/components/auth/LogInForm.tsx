import { Button, Text, TextInput, View } from "react-native";
import { Controller, useForm } from "react-hook-form";
import { formStyles } from "../styles";
import FieldErrorMessage from "../form/FieldErrorMessage";

type LoginForm = {
    email: string,
    password: string
}

export default function LogInForm() {
    const { 
        control, 
        handleSubmit,
        formState: { errors, isValid },
    } = useForm<LoginForm>({
        mode: "all",
        defaultValues: {
            email: '',
            password: ''
        }
    });

    function handlePressLogInButton() {
        console.log('Logging in - valid? ', isValid);
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

            <Button title="Log in" onPress={handleSubmit(handlePressLogInButton)}/>
        </>
    )
}