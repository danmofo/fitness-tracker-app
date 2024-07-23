import { API_BASE_URL } from "@/config";
import { AuthenticatedRequest } from "./auth";

type LogWeightRequest = {
    weight: number
} & AuthenticatedRequest;

type LogWeightResponse = {
    success: boolean
}

export async function logWeight(request: LogWeightRequest): Promise<LogWeightResponse> {
    try {
        const response = await fetch(`${API_BASE_URL}/user/weight/`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-Auth-Token': request.sessionToken
            },
            body: JSON.stringify({
                weight: request.weight
            })
        });

        return (await response.json());
    } catch (e) {
        return {
            success: false
        }
    }
}

export type UserBodyWeight = {
    id: number,
    userId: number,
    weight: number,
    loggedOn: string
}

type ListBodyWeightResponse = {
    bodyWeights: UserBodyWeight[]
}


export async function listBodyWeight(request: AuthenticatedRequest): Promise<ListBodyWeightResponse> {
    try {
        const response = await fetch(`${API_BASE_URL}/user/weight/`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'X-Auth-Token': request.sessionToken
            }
        });

        return (await response.json());
    } catch (e) {
        return {
            bodyWeights: []
        }
    }
}