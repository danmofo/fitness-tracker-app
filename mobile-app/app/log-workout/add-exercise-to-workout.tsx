import Button from "@/components/Button";
import FieldErrorMessage from "@/components/form/FieldErrorMessage";
import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import { formStyles } from "@/components/styles";
import { useAuthStore } from "@/store/auth-store";
import { Controller, useForm } from "react-hook-form";
import { Text, TextInput, View } from "react-native";


export type AddExerciseForm = {
    weight: number,
    sets: number,
    reps: number,
    notes: string,
    equipment: string
}

export default function AddExerciseToWorkoutScreen() {
    const authStore = useAuthStore();

    const { 
        control, 
        handleSubmit,
        formState: { errors, isValid },
        getValues
    } = useForm<AddExerciseForm>({
        mode: "all",
        defaultValues: {
            weight: 0,
            sets: 1,
            reps: 1,
            notes: '',
            equipment: ''
        }
    });

    async function handleAddSet() {
        console.log('Adding set', getValues());
    }

    return (
        <ScreenLayout screenHasHeader={true}>
            <Box padding={20}>
                {/* Weight */}
                <View style={formStyles.inputContainer}>
                    <Text style={formStyles.label}>Weight</Text>
                    <Controller 
                        control={control}
                        name="weight"
                        rules={{}} 
                        render={({ field: { onChange, value } }) => (
                            <TextInput 
                                value={String(value)}
                                onChangeText={onChange}
                                style={formStyles.input}
                            />
                        )}
                    />
                    <FieldErrorMessage fieldError={errors.weight} />
                </View>

                {/* Sets */}
                <View style={formStyles.inputContainer}>
                    <Text style={formStyles.label}>Sets</Text>
                    <Controller 
                        control={control}
                        name="sets"
                        rules={{}} 
                        render={({ field: { onChange, value } }) => (
                            <TextInput 
                                value={String(value)}
                                onChangeText={onChange}
                                style={formStyles.input}
                            />
                        )}
                    />
                    <FieldErrorMessage fieldError={errors.sets} />
                </View>

                {/* Reps */}
                <View style={formStyles.inputContainer}>
                    <Text style={formStyles.label}>Reps</Text>
                    <Controller 
                        control={control}
                        name="reps"
                        rules={{}} 
                        render={({ field: { onChange, value } }) => (
                            <TextInput 
                                value={String(value)}
                                onChangeText={onChange}
                                style={formStyles.input}
                            />
                        )}
                    />
                    <FieldErrorMessage fieldError={errors.reps} />
                </View>

                {/* Notes */}
                <View style={formStyles.inputContainer}>
                    <Text style={formStyles.label}>Notes</Text>
                    <Controller 
                        control={control}
                        name="notes"
                        rules={{}} 
                        render={({ field: { onChange, value } }) => (
                            <TextInput 
                                value={String(value)}
                                onChangeText={onChange}
                                style={formStyles.input}
                            />
                        )}
                    />
                    <FieldErrorMessage fieldError={errors.notes} />
                </View>

                {/* Equipment */}
                <View style={formStyles.inputContainer}>
                    <Text style={formStyles.label}>Equipment</Text>
                    <Controller 
                        control={control}
                        name="equipment"
                        rules={{}} 
                        render={({ field: { onChange, value } }) => (
                            <TextInput 
                                value={String(value)}
                                onChangeText={onChange}
                                style={formStyles.input}
                            />
                        )}
                    />
                    <FieldErrorMessage fieldError={errors.equipment} />
                </View>

                <Button 
                    title="Add"
                    onPress={handleSubmit(handleAddSet)}
                />
            </Box>
        </ScreenLayout>
    )
}