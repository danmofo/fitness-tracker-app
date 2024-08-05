import { logExercise } from "@/api/workout";
import Button from "@/components/Button";
import FieldErrorMessage from "@/components/form/FieldErrorMessage";
import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import { formStyles } from "@/components/styles";
import { useAuthStore } from "@/store/auth-store";
import { useWorkoutStore } from "@/store/workout-store";
import { router } from "expo-router";
import { useState } from "react";
import { Controller, useForm } from "react-hook-form";
import { ActivityIndicator, Text, TextInput, View } from "react-native";


export type AddExerciseForm = {
    weight: number,
    sets: number,
    reps: number,
    notes: string,
    equipment: string
}

export default function AddExerciseToWorkoutScreen() {
    const authStore = useAuthStore();
    const workoutStore = useWorkoutStore();
    const [isLoading, setIsLoading] = useState(false);

    const { 
        control, 
        handleSubmit,
        formState: { errors },
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
        setIsLoading(true);

        const formValues = getValues();
        const { success } = await logExercise({
            workoutId: workoutStore.workoutId!,
            exerciseId: workoutStore.currentExercise?.id!,
            weight: formValues.weight,
            sets: formValues.sets,
            reps: formValues.reps,
            notes: formValues.notes,
            equipment: formValues.equipment.split(','),
            sessionToken: authStore.sessionToken
        });

        if(!success) {
            console.error('Failed to log workout');
            // todo: Handle validation errors.
        } else {
            router.dismiss();
        }

        setIsLoading(false);
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
                        rules={{
                            min: {
                                value: 1,
                                message: 'Weight must be at least 1KG'
                            }
                        }} 
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

                {
                    isLoading ? 
                    <ActivityIndicator size={32} /> :
                    <Button 
                        title="Add"
                        onPress={handleSubmit(handleAddSet)}
                    />
                }
                
            </Box>
        </ScreenLayout>
    )
}