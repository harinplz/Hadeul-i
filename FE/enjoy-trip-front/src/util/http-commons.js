// 1. axios 라이브러리 불러오기
import axios from "axios";

// 2. axios 객체 생성
export default axios.create({
  baseURL: "http://localhost:80",
});
