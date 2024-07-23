import { API_BASE_URL } from "@/config";
import { AuthenticatedRequest } from "./auth";

export type Exercise = {
    id: number,
    name: string
    type: ExerciseType,
    brand: string
}

type ExerciseType = 'FREE_WEIGHT' | null | undefined;

type ListExerciseResponse = {
    exercises: Exercise[]
}

export async function listExercises(request: AuthenticatedRequest): Promise<ListExerciseResponse> {
    try {
        const response = await fetch(`${API_BASE_URL}/workout/exercise`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'X-Auth-Token': request.sessionToken
            }
        });

        return (await response.json());
    } catch (e) {
        return {
            exercises: []
        }
    }
}