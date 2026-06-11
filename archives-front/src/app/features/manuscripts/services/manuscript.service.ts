import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class ManuscriptService {
    private baseUrl = "http://localhost:8080/manuscripts";
    private importUrl = "http://localhost:8080/imports/csv";

    constructor(private http: HttpClient) {}

    getAll(): Observable<any[]> {
        return this.http.get<any[]>(this.baseUrl);
    }

    getById(id: number): Observable<any> {
        return this.http.get<any>(`${this.baseUrl}/${id}`);
    } 

    importCsv(file: File): Observable<string> {
        const formData = new FormData();
        formData.append('file', file);

        return this.http.post(this.importUrl, formData, { responseType: 'text' });
    }
}
