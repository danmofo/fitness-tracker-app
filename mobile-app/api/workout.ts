import { API_BASE_URL } from "@/config";
import { AuthenticatedRequest } from "./auth";

type StartWorkoutResponse = {
    workoutId: number | null
}

export async function startWorkout(request: AuthenticatedRequest): Promise<StartWorkoutResponse> {
    try {
        const response = await fetch(`${API_BASE_URL}/workout/`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-Auth-Token': request.sessionToken
            }
        });
        return (await response.json());
    } catch (e) {
        console.log(e);
        return {
            workoutId: null
        }
    }
}

type ListWorkoutExercisesRequest = {
    workoutId: number
} & AuthenticatedRequest;

type ListWorkoutExercisesResponse = {
    exercises: ExerciseWithCompletedSets[]
}

export type ExerciseWithCompletedSets = {
    id: number,
    name: string,
    completed: CompletedSet[]
}

export type CompletedSet = {
    id: number,
    weight: number,
    sets: number,
    reps: number,
    notes?: string,
    equipment?: string[]
}

export async function listWorkoutExercises(request: ListWorkoutExercisesRequest): Promise<ListWorkoutExercisesResponse> {
    try {
        const response = await fetch(`${API_BASE_URL}/workout/${request.workoutId}/exercise`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'X-Auth-Token': request.sessionToken
            }
        });
        return (await response.json());
    } catch (e) {
        console.log(e);
        return {
            exercises: []
        }
    }
}

type ListCompletedSetsForExerciseRequest = {
    workoutId: number,
    exerciseId: number
} & AuthenticatedRequest;

type ListCompletedSetsForExerciseResponse = {
    completedSets: CompletedSet[]
}

export async function listCompletedSetsForExercise(request: ListCompletedSetsForExerciseRequest): Promise<ListCompletedSetsForExerciseResponse> {
    try {
        const response = await fetch(`${API_BASE_URL}/workout/${request.workoutId}/exercise/${request.exerciseId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'X-Auth-Token': request.sessionToken
            }
        });
        return (await response.json());
    } catch (e) {
        console.log(e);
        return {
            completedSets: []
        }
    }
}