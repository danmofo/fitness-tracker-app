import { create } from "zustand";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { createJSONStorage, persist } from "zustand/middleware";
import { Exercise } from "@/api/exercise";

export type WorkoutState = {
    workoutId: number | null,
    startWorkout: (workoutId: number) => void,
    hasWorkoutInProgress: () => boolean,
    currentExercise: Exercise | null,
    setCurrentExercise: (exercise: Exercise) => void,
    clear: () => void
}

export const useWorkoutStore = create(
    persist<WorkoutState>(
        (set, get) => ({
            workoutId: null,
            currentExercise: null,
            startWorkout(workoutId: number) {
                set({ workoutId });
            },
            hasWorkoutInProgress() {
                const { workoutId } = get();
                return workoutId !== null;
            },
            setCurrentExercise(exercise: Exercise) {
                set({
                    currentExercise: exercise
                });
            },
            clear() {
                set({ 
                    workoutId: null,
                    currentExercise: null
                });
            }
        }),
        {
            name: 'workout-storage',
            storage: createJSONStorage(() => AsyncStorage)
        }
    )
);