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