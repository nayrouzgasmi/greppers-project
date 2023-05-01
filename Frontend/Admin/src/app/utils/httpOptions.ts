import { HttpHeaders } from "@angular/common/http";

export const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization:
        'Bearer ' +
        'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJraGFsaWwuY2hldHRhb3VpMDZAZ21haWwuY29tIiwiZXhwIjoxNjgzNTcyNTA0fQ.Ji-N0L_8aTjyuuiU_TaqKP5M1k6c9r1qXHbNJgd6--NpWLEgeadlG3Yms2aSYGooh7iGA0f9JvVjht0XC_3Bog',
    }),
  };