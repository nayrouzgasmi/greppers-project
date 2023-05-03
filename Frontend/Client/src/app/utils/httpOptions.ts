import { HttpHeaders } from "@angular/common/http";

export const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization:
        'Bearer '+'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYXlyb3V6Z2FzbWlAZ21haWwuY29tIiwiZXhwIjoxNjgzOTgzMjEwfQ.LFVDetRPGeuKHmiujd_bV-0gMdRFQ7Sp5uYHsACmQ7JnizknPnZfqgvo5Xb2HaCnMVNd8vnd3_mmf-fnuOVTZg'
    }),
  };