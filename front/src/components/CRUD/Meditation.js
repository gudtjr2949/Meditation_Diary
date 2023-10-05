import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import MeditationFooter from "./MeditationFooter";
import "../../assets/css/Meditation.css"

function MeditationDetail() {
  const location = useLocation();
  const meditationData = location.state.meditationData;
  const [currentAudioIndex, setCurrentAudioIndex] = useState(0);
  const [audioPlayer, setAudioPlayer] = useState(null);
  const [isPlaying, setIsPlaying] = useState(false); // 재생 상태를 나타내는 상태값
  const [selectedTab, setSelectedTab] = useState("Feed"); // 기본적으로 Feed 탭 선택
  console.log("meditationDetail에서 Data :", meditationData);

  useEffect(() => {
    // Audio 객체 생성
    const audio = new Audio();
    audio.src = meditationData.meditationMedia[currentAudioIndex].audioUrl;

    // 음성 파일이 끝나면 다음 음성 파일 재생
    audio.addEventListener("ended", () => {
      if (currentAudioIndex < meditationData.meditationMedia.length - 1) {
        setCurrentAudioIndex(currentAudioIndex + 1);
        audio.src =
          meditationData.meditationMedia[currentAudioIndex + 1].audioUrl;
        audio.play();
      }
    });

    setAudioPlayer(audio);

    // 컴포넌트가 언마운트될 때 리소스 해제
    return () => {
      audio.pause();
      audio.removeEventListener("ended", () => {});
    };
  }, [currentAudioIndex, meditationData]);

  const handlePlayAudio = () => {
    if (audioPlayer) {
      audioPlayer.play();
      setIsPlaying(true); // 재생 상태를 true로 설정
    }
  };
  
  const handlePauseAudio = () => {
    if (audioPlayer) {
      audioPlayer.pause();
      setIsPlaying(false); // 재생 상태를 false로 설정
    }
  };

  return (
    <div className="meditation-detail-container"> {/* 스타일 클래스를 적용 */}
      <img
        src={meditationData.meditationMedia[currentAudioIndex].imageUrl}
        alt="Meditation"
        className="meditation-image-container"
      />
      <MeditationFooter
        setSelectedTab={setSelectedTab}
        isPlaying={isPlaying}
        onPlayClick={handlePlayAudio}
        onPauseClick={handlePauseAudio}
      />
    </div>
  );
}

export default MeditationDetail;
