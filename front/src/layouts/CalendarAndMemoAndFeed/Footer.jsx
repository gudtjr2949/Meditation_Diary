import React, { useContext } from 'react';
import { ThemeContext, themes } from 'contexts/ThemeContext';
import { useNavigate } from 'react-router-dom';
import '../../assets/css/Footer.css';

function Footer({ setSelectedTab }) {
  const navigate = useNavigate();
  const { theme, changeTheme } = useContext(ThemeContext);

  const toggleTheme = () => {
    // 현재 테마가 "light"이면 "dark"로, 그 반대면 "light"로 변경
    const newTheme = theme === themes.light ? themes.dark : themes.light;
    console.log(newTheme, '으로 배경색 변경')
    changeTheme(newTheme);
  };

  const handleCreateClick = () => {
    navigate("/crud");
  };

  const handleFeedClick = () => {
    setSelectedTab("Feed");
    navigate("/frame")
  };

  // 테마에 따라 클래스를 동적으로 설정
  const footerClass = theme === themes.light ? '' : 'dark-theme';

  return (
    <div className={`footer ${footerClass}`}>
      <button className="footer-btn btn-left" onClick={toggleTheme}>
        <img src="https://s3.ap-northeast-2.amazonaws.com/b205.s3test.bucket/footer/footer-left.png" />
      </button>
      <button className="footer-btn btn-center" onClick={handleCreateClick}>
        <img src="https://s3.ap-northeast-2.amazonaws.com/b205.s3test.bucket/footer/footer-center.png" />
      </button>
      <button className="footer-btn btn-right" onClick={handleFeedClick}>
        <img src="https://s3.ap-northeast-2.amazonaws.com/b205.s3test.bucket/footer/footer-right.png" />
      </button>
    </div>
  );
}

export default Footer;
