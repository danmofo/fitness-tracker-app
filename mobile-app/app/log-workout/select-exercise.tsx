import { Exercise, listExercises } from "@/api/exercise";
import Button from "@/components/Button";
import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import ExerciseList from "@/components/log-workout/ExerciseList";
import SearchBox from "@/components/log-workout/SearchBox";
import Heading from "@/components/text/Heading";
import { useAuthStore } from "@/store/auth-store";
import { useWorkoutStore } from "@/store/workout-store";
import { router } from "expo-router";
import { useEffect, useState } from "react";

export default function SelectExerciseScreen() {
    console.log('info: Render SelectExerciseScreen');

    const sessionToken = useAuthStore(state => state.sessionToken);
    const setCurrentExercise = useWorkoutStore(state => state.setCurrentExercise);
    
    const [query, setQuery] = useState<string>('');
    const [exercises, setExercises] = useState<Exercise[]>([]);

    useEffect(() => {
        (async () => {
            const response = await listExercises({ sessionToken });
            setExercises(response.exercises);
        })();
    }, []);

    function handleChangeQuery(newQuery: string) {
        setQuery(newQuery);
    }

    function handleSelectExercise(selectedExercise: Exercise) {
        console.log('Selected', selectedExercise);
        setCurrentExercise(selectedExercise);
        router.navigate('/log-workout/workout-add-exercise');
    }

    const filteredExercises = filterExercises(exercises, query);

    return (
        <ScreenLayout screenHasHeader={true}>
            <Box padding={20}>
                <SearchBox 
                    query={query}  
                    onChangeQuery={handleChangeQuery}
                />
                <ExerciseList 
                    exercises={filteredExercises} 
                    onSelectExercise={handleSelectExercise}
                />
            </Box>
        </ScreenLayout>
    )
}

function filterExercises(exercises: Exercise[], query: string) {
    if(query === '') {
        return exercises;
    }

    return exercises.filter(exercise => {
        return  String(exercise.name).includes(query) ||
                String(exercise.brand).includes(query)
    });
}