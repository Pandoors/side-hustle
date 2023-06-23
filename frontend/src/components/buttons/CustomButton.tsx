import React from "react";

function CustomButton({
  oppositeColors,
  text,
  onClick,
  style,
}: {
  oppositeColors?: boolean;
  onClick?: () => void;
  style?: any;
  text: string;
}) {
  return (
    <>
      <div
        className={
          oppositeColors
            ? "job_card_apply_button_opposite"
            : "job_card_apply_button_purple"
        }
        style={style}
        onClick={onClick}
      >
        {text}
      </div>
    </>
  );
}

export default CustomButton;
