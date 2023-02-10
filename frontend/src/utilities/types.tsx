export type Temp = {
    id: number,
    firstName: string,
    lastName: string,
    jobs: Job[];
    username: string,
    password: string;
};

export type Job = {
    id: number,
    name: string,
    startDate: string,
    endDate: string, 
    temp: Temp; //use temp type
};

