import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { catchError, Observable, throwError } from "rxjs";
import { DataServiceService } from "../Services/data-service.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor{

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
       if(req.headers.get("No-Auth") == 'True'){
            return next.handle(req.clone());
       }
       const token = this.dataService.getToken();
       req = this.addToken(req, token);
       return next.handle(req).pipe(
        catchError(
            (err: HttpErrorResponse) => {
                if (err.status === 401) {
                    this.router.navigate(['/login']);
                    return throwError("Something went wrong, Please try again");
                } else if (err.status === 403) {
                    this.router.navigate(['/login']);
                    return throwError("Something went wrong, Please try again");
                }
                return throwError("Some thing is wrong");
            })
    );
    }


    addToken(request: HttpRequest<any>, token: string): HttpRequest<any> {
        return request.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`
             }
        });
    }

    constructor(private dataService: DataServiceService,
        private router: Router) {}

}