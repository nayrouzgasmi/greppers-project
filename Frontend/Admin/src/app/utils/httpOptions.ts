import { HttpHeaders } from "@angular/common/http";

export const httpOptions = {
    headers: new HttpHeaders({
      // 'Content-Type': 'multipart/form-data',
      Authorization:
        'Bearer ' +
        'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJraGFsaWwuY2hldHRhb3VpMDZAZ21haWwuY29tIiwiZXhwIjoxNjgzODEzNzIzfQ.U_UOE8z3IK_krIruiqT2W25jUb2xoThh4YwNEYSUranxfo2lpQrGuBLTUhifJCL1Gc7soJkUVEmmPfOX7b1NgQ',
    }),
  };