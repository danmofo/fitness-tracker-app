import { UserBodyWeight, listBodyWeight, logWeight } from "@/lib/api/log-weight";
import Button from "@/lib/components/Button";
import FieldErrorMessage from "@/lib/components/form/FieldErrorMessage";
import Box from "@/lib/components/layout/Box";
import ScreenLayout from "@/lib/components/layout/ScreenLayout";
import { formStyles } from "@/lib/components/styles";
import Heading from "@/lib/components/text/Heading";
import { useAuthStore } from "@/lib/store/auth-store";
import { useEffect, useState } from "react";
import { Controller, useForm } from "react-hook-form";
import { ActivityIndicator, FlatList, Pressable, StyleSheet, Text, TextInput, View } from "react-native";

// todo: We should load the user's most recent weight and pre-fill the input field

type LogWeightForm = {
    weight: number
}

export default function LogWeightScreen() {
    const sessionToken = useAuthStore(state => state.sessionToken);

    const [loading, setLoading] = useState<boolean>(false);
    const [complete, setComplete] = useState<boolean>(false);
    const [bodyWeights, setBodyWeights] = useState<UserBodyWeight[]>()

    const { 
        control, 
        handleSubmit,
        formState: { errors },
        getValues
    } = useForm<LogWeightForm>({
        mode: "all",
        defaultValues: {}
    });

    async function handlePressLogWeightButton() {
        setLoading(true);

        const weight = getValues().weight;
        const { success } = await logWeight({
            sessionToken,
            weight
        });

        setLoading(false);

        if(success) {
            setComplete(true);
        }
    }

    useEffect(() => {
        (async () => {
            const { bodyWeights } = await listBodyWeight({ sessionToken });
            setBodyWeights(bodyWeights);
        })();
    }, []);

    if(complete) {
        return (
            <ScreenLayout screenHasHeader={false}>
                <Box padding={20}>
                    <Text>Thank you for logging your weight.</Text>
                    <Button title="Go home" href="/dashboard/" />
                </Box>
            </ScreenLayout>
        )
    }
    
    return (
        <ScreenLayout screenHasHeader={false}>
            <Box padding={20}>
                <Heading>Log weight</Heading>                
                <View>
                    <Controller 
                        control={control}
                        name="weight"
                        rules={{
                            required: 'Please enter a weight'
                        }} 
                        render={({ field: { onChange, value } }) => (
                            <View>
                                <TextInput 
                                    value={value?.toString()}
                                    onChangeText={onChange}
                                    style={formStyles.input}
                                    placeholder="Enter weight"
                                    keyboardType="number-pad"
                                />
                                <Text style={styles.inputUnitType}>kg</Text>
                            </View>
                        )}
                    />
                    <FieldErrorMessage fieldError={errors.weight} />
                </View>

                {
                    loading ? 
                    <ActivityIndicator size={32} /> :
                    <Button title="Log weight" onPress={handleSubmit(handlePressLogWeightButton)} />
                }
            </Box>

            <Box>
                <Box padding={20}>
                    <Heading>Your weight history</Heading>
                </Box>

                <View style={styles.listHeadingContainer}>
                    <Text style={styles.listHeading}>Weight</Text>
                    <Text style={styles.listHeading}>Date</Text>
                </View>
                {
                    bodyWeights?.length ?
                    <FlatList
                        data={bodyWeights}
                        keyExtractor={(item) => {
                            return item.id.toString()
                        }}
                        renderItem={({ item }) => {
                            return (
                                <View style={styles.listItem}>
                                    <Text style={styles.listItemDataWeight}>{item.weight}kg</Text>
                                    <Text style={styles.listItemDataLoggedOn}>{item.loggedOn}</Text>
                                </View>
                                
                            )
                        }}
                    />:
                    <Text>You haven't logged any body weights yet.</Text>
                }
            </Box>
        </ScreenLayout>
    )
}

const styles = StyleSheet.create({
    inputUnitType: {
        backgroundColor: '#DDD',
        position: 'absolute',
        right: 0,
        height: '100%',
        width: 50,
        textAlign: 'center',
        textAlignVertical: 'center',
        fontSize: 20
    },
    listItem: {
        padding: 20,
        flexDirection: 'row',
        borderBottomWidth: 1,
        borderColor: '#ddd'
    },
    listItemDataWeight: {
        flex: 1,
        
    },
    listItemDataLoggedOn: {
        flex: 1,
    },
    listHeadingContainer: {
        flexDirection: 'row',
        paddingHorizontal: 20
    },
    listHeading: {
        fontSize: 18,
        fontWeight: 'bold',
        flex: 1,
        borderBottomWidth: 1,
        borderColor: '#ddd',
        paddingVertical: 10
    }
});