import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Waiting.css'; // 스타일 파일을 불러옵니다.
import axios from "axios";

function Waiting() {
  const navigate = useNavigate();
  const [audioPlayed, setAudioPlayed] = useState(false);
  let audio = null;

  const playAudio = () => {
    if (audio) {
      audio.play();
    }
  };

  useEffect(() => {
    // 오디오 파일 생성
    audio = new Audio('/assets/naration/loading_naration.wav');

    // 오디오 재생 종료 시 페이지 이동 예약
    audio.addEventListener('ended', () => {
      setAudioPlayed(true);
    });

    // 컴포넌트 언마운트 시 리소스 해제
    return () => {
      if (audio) {
        audio.pause();
        audio.removeEventListener('ended', () => {});
      }
    };

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  // 오디오 재생 후 페이지 이동
  useEffect(() => {
    if (audioPlayed) {
      const memberIdx = parseInt(localStorage.getItem('memberIdx'),10);
      const accessToken = localStorage.getItem('accessToken');

      // 게시글 리스트를 가져오는 요청
      axios.get(`https://j9b205.p.ssafy.io/api/meditation/list/${memberIdx}`, {
        headers: {
          'Authorization': `Bearer ${accessToken}`,
        }
      })
      .then((response) => {
        if (response.data.meditationList.length === 0) {
          navigate('/welcome');
        } else {
          const idx = response.data.meditationList[response.data.meditationList.length-1].meditationIdx
          navigate(`/meditation/${idx}`);
        }
      })
      .catch((error) => {
        console.error("Error fetching meditation list:", error);
      });
    }
  }, [audioPlayed, navigate]);

  return (
    <div className="audio-player">
      <p className="loading-text">명상 파일 로딩 중입니다...</p>
      <button onClick={playAudio}>오디오 재생</button>
    </div>
  );
}

export default Waiting;
