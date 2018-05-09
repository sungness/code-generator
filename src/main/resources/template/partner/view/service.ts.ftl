import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ${table.upperCaseName}Service {

    constructor(private http: HttpClient) {}

    create(${table.camelCaseName}): Observable<any> {
      return this.http.post('/api${viewPath}/new', ${table.camelCaseName}, {observe: 'response'});
    }
    update(${table.camelCaseName}): Observable<any> {
        return this.http.put('/api${viewPath}/edit', ${table.camelCaseName}, {observe: 'response'});
    }
    detail(id: number): Observable<any> {
      return this.http.get('/api${viewPath}/detail', {params: {id: id.toString()}, observe: 'response'});
    }

    delete(ids): Observable<any> {
        return this.http.delete('/api${viewPath}/delete', {params: {ids: ids}, observe: 'response'});
    }
}
